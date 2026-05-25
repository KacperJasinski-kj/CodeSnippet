package SERVICE;

import DAO.SnippetDAO;
import MODEL.Snippet;

import java.util.List;

/**
 * Servicio de snippets.
 *
 * Contiene las validaciones y la lógica relacionada con los fragmentos de código.
 */
public class SnippetService extends BaseValidationService {

    private final SnippetDAO snippetDAO = new SnippetDAO();

    public void save(Snippet snippet) {
        validateSnippet(snippet);
        snippetDAO.save(snippet);
    }

    public Snippet findById(Long id) {
        return snippetDAO.findById(id);
    }

    public List<Snippet> findAll() {
        return snippetDAO.findAll();
    }

    public Snippet update(Snippet snippet) {
        validateSnippet(snippet);
        return snippetDAO.update(snippet);
    }

    public void delete(Long id) {
        snippetDAO.delete(id);
    }

    public List<Snippet> searchByKeyword(String keyword) {
        validateRequiredText(keyword, "búsqueda");
        return snippetDAO.searchByKeyword(keyword);
    }

    public List<Snippet> findByLanguage(Long languageId) {
        return snippetDAO.findByLanguage(languageId);
    }

    public List<Snippet> findByCategory(Long categoryId) {
        return snippetDAO.findByCategory(categoryId);
    }

    public List<Snippet> findByTagName(String tagName) {
        validateRequiredText(tagName, "etiqueta");
        return snippetDAO.findByTagName(tagName);
    }

    /**
     * Valida los campos principales de un snippet.
     *
     * @param snippet snippet que se quiere validar
     */
    private void validateSnippet(Snippet snippet) {
        validateRequiredObject(snippet, "snippet");
        validateRequiredText(snippet.getTitle(), "título");
        validateRequiredText(snippet.getDescription(), "descripción");
        validateRequiredText(snippet.getSourceCode(), "código fuente");
        validateRequiredObject(snippet.getUser(), "usuario");
        validateRequiredObject(snippet.getLanguage(), "lenguaje");
        validateRequiredObject(snippet.getCategory(), "categoría");
    }
}
