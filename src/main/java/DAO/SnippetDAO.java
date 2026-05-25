package DAO;

import CONFIG.HibernateUtil;
import MODEL.Snippet;
import jakarta.persistence.EntityManager;

import java.util.List;

/**
 * DAO específico para la entidad Snippet.
 *
 * Además del CRUD heredado de GenericDAO, añade búsquedas especiales
 * para localizar fragmentos de código por texto, lenguaje, categoría o etiqueta.
 */
public class SnippetDAO extends GenericDAO<Snippet> {

    /**
     * Constructor que indica al DAO genérico que trabajará con Snippet.
     */
    public SnippetDAO() {
        super(Snippet.class);
    }

    /**
     * Busca snippets por una palabra clave.
     * <p>
     * La búsqueda se hace en el título, descripción y código fuente.
     *
     * @param keyword palabra clave que se quiere buscar
     * @return lista de snippets encontrados
     */
    public List<Snippet> searchByKeyword(String keyword) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT s FROM Snippet s " +
                                    "WHERE LOWER(s.title) LIKE LOWER(:keyword) " +
                                    "OR LOWER(s.description) LIKE LOWER(:keyword) " +
                                    "OR LOWER(s.sourceCode) LIKE LOWER(:keyword)",
                            Snippet.class
                    ).setParameter("keyword", "%" + keyword + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Busca snippets por lenguaje de programación.
     *
     * @param languageId id del lenguaje
     * @return lista de snippets de ese lenguaje
     */
    public List<Snippet> findByLanguage(Long languageId) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT s FROM Snippet s WHERE s.language.id = :languageId",
                            Snippet.class
                    ).setParameter("languageId", languageId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Busca snippets por categoría.
     *
     * @param categoryId id de la categoría
     * @return lista de snippets de esa categoría
     */
    public List<Snippet> findByCategory(Long categoryId) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT s FROM Snippet s WHERE s.category.id = :categoryId",
                            Snippet.class
                    ).setParameter("categoryId", categoryId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Busca snippets que tengan una etiqueta concreta.
     *
     * @param tagName nombre de la etiqueta
     * @return lista de snippets asociados a esa etiqueta
     */
    public List<Snippet> findByTagName(String tagName) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT s FROM Snippet s JOIN s.tags t WHERE LOWER(t.name) = LOWER(:tagName)",
                            Snippet.class
                    ).setParameter("tagName", tagName)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}