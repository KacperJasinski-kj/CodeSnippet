package DAO;

import CONFIG.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

/**
 * DAO genérico para reutilizar las operaciones CRUD básicas.
 *
 * T representa la entidad con la que se trabaja:
 * User, Snippet, Language, Category o Tag.
 *
 * Esta clase se usa como base para los DAOs concretos.
 */
public class GenericDAO<T> {

    /**
     * Clase de la entidad que manejará este DAO.
     * Por ejemplo: User.class, Snippet.class, Category.class...
     */
    private final Class<T> entityClass;

    /**
     * Constructor del DAO genérico.
     *
     * @param entityClass clase de la entidad
     */
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Guarda o actualiza una entidad.
     *
     * Este método es útil para los paneles Swing, porque tus Panel llaman
     * directamente a saveOrUpdate().
     *
     * Si la entidad no existe, la guarda.
     * Si la entidad ya existe, la actualiza.
     *
     * @param entity entidad que se quiere guardar o actualizar
     * @return entidad gestionada por Hibernate
     */
    public T saveOrUpdate(T entity) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            T managedEntity = em.merge(entity);
            tx.commit();
            return managedEntity;
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza una entidad existente.
     *
     * Se usa normalmente desde los Service.
     *
     * @param entity entidad con los nuevos datos
     * @return entidad actualizada
     */

    /**
     * Lista todos los registros de una entidad.
     *
     * @return lista completa de entidades
     */
    public List<T> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            return em.createQuery(jpql, entityClass).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Elimina una entidad completa.
     *
     * Este método es útil para los paneles Swing, porque algunos paneles
     * crean un objeto con solo el ID y llaman a delete(objeto).
     *
     * @param entity entidad que se quiere eliminar
     */
    public void delete(T entity) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            T managedEntity = em.merge(entity);
            em.remove(managedEntity);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
