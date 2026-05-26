package CONFIG;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Clase de utilidad para trabajar con Hibernate/JPA.
 *
 * Su función principal es crear una única instancia de EntityManagerFactory
 * para toda la aplicación y proporcionar EntityManager cuando se necesiten.
 */
public class HibernateUtil {

    // Nombre de la unidad de persistencia definida en persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "codesnippetPU";

    // EntityManagerFactory se crea una sola vez porque es un objeto pesado.
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    /**
     * Devuelve un nuevo EntityManager.
     *
     * El EntityManager se usa para hacer operaciones con la base de datos:
     * guardar, buscar, actualizar y eliminar.
     *
     * @return un EntityManager nuevo
     */
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Cierra la fábrica de EntityManager.
     *
     * Este método debería llamarse al cerrar la aplicación.
     */
    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
