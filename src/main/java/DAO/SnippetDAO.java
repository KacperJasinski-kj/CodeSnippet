package DAO;

import CONFIG.HibernateUtil;
import MODEL.Snippet;
import jakarta.persistence.EntityManager;

import java.util.List;

/**
 * DAO específico para la entidad Snippet.
 *
 * Además del CRUD heredado de GenericDAO, añade búsquedas especiales
 * para localizar fragmentos de código por texto, título, lenguaje,
 * categoría o etiqueta.
 */
public class SnippetDAO extends GenericDAO<Snippet> {

    /**
     * Constructor que indica al DAO genérico que trabajará con Snippet.
     */
    public SnippetDAO() {
        super(Snippet.class);
    }

    /**
     * Sobrescribe el findAll del GenericDAO.
     *
     * En Snippet hace falta cargar también las relaciones:
     * usuario, lenguaje, categoría y etiquetas.
     *
     * Si no se hace con JOIN FETCH, Hibernate cierra la sesión y luego
     * la vista no puede acceder a s.getLanguage().getName(), etc.
     */
    @Override
    public List<Snippet> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT DISTINCT s FROM Snippet s " +
                            "LEFT JOIN FETCH s.user " +
                            "LEFT JOIN FETCH s.language " +
                            "LEFT JOIN FETCH s.category " +
                            "LEFT JOIN FETCH s.tags",
                    Snippet.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Busca snippets por título.
     *
     * Este método es necesario porque SnippetPanel llama a searchByTitle().
     */
    public List<Snippet> searchByTitle(String title) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT DISTINCT s FROM Snippet s " +
                                    "LEFT JOIN FETCH s.user " +
                                    "LEFT JOIN FETCH s.language " +
                                    "LEFT JOIN FETCH s.category " +
                                    "LEFT JOIN FETCH s.tags " +
                                    "WHERE LOWER(s.title) LIKE LOWER(:title)",
                            Snippet.class
                    )
                    .setParameter("title", "%" + title + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

}