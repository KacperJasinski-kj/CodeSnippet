package SERVICE;

import DAO.CategoryDAO;
import MODEL.Category;
import java.util.List;

/**
 * Servicio de categorías.
 */
public class CategoryService extends BaseValidationService {

    private final CategoryDAO categoryDAO = new CategoryDAO();

    public void save(Category category) {
        validateRequiredObject(category, "categoría");
        validateRequiredText(category.getName(), "nombre");
        categoryDAO.save(category);
    }

    public Category findById(Long id) {
        return categoryDAO.findById(id);
    }

    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    public Category update(Category category) {
        validateRequiredObject(category, "categoría");
        validateRequiredText(category.getName(), "nombre");
        return categoryDAO.update(category);
    }

    public void delete(Long id) {
        categoryDAO.delete(id);
    }
}

