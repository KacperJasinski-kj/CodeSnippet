import java.util.List;

/**
 * Controlador de snippets.
 *
 * Esta clase conecta la interfaz Swing con la capa de servicio.
 * La vista no debería llamar directamente al DAO.
 */
public class SnippetController {

    private final SnippetService snippetService = new SnippetService();

    /**
     * Guarda un snippet desde la interfaz.
     *
     * @param snippet snippet que viene del formulario Swing
     * @return true si se guardó correctamente
     */
    public boolean saveSnippet(Snippet snippet) {
        try {
            snippetService.save(snippet);
            return true;
        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
            return false;
        } catch (RuntimeException e) {
            System.out.println("Error al guardar el snippet: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza un snippet existente.
     *
     * @param snippet snippet con los datos modificados
     * @return true si se actualizó correctamente
     */
    public boolean updateSnippet(Snippet snippet) {
        try {
            snippetService.update(snippet);
            return true;
        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
            return false;
        } catch (RuntimeException e) {
            System.out.println("Error al actualizar el snippet: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un snippet por id.
     *
     * @param id id del snippet
     * @return true si se eliminó correctamente
     */
    public boolean deleteSnippet(Long id) {
        try {
            snippetService.delete(id);
            return true;
        } catch (RuntimeException e) {
            System.out.println("Error al eliminar el snippet: " + e.getMessage());
            return false;
        }
    }

    /**
     * Devuelve todos los snippets para mostrarlos en una JTable.
     *
     * @return lista de snippets
     */
    public List<Snippet> getAllSnippets() {
        return snippetService.findAll();
    }

    /**
     * Busca snippets por palabra clave.
     *
     * @param keyword palabra escrita en el buscador
     * @return lista de snippets encontrados
     */
    public List<Snippet> searchSnippets(String keyword) {
        return snippetService.searchByKeyword(keyword);
    }
}
