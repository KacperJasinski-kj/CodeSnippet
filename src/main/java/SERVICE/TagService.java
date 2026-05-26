package SERVICE;
import DAO.TagDAO;
import MODEL.Tag;
import java.util.List;

/**
 * Servicio de etiquetas.
 */
public class TagService extends BaseValidationService {

    private final TagDAO tagDAO = new TagDAO();

    public void save(Tag tag) {
        validateRequiredObject(tag, "etiqueta");
        validateRequiredText(tag.getName(), "nombre");
        tagDAO.save(tag);
    }

    public Tag findById(Long id) {
        return tagDAO.findById(id);
    }

    public List<Tag> findAll() {
        return tagDAO.findAll();
    }

    public Tag update(Tag tag) {
        validateRequiredObject(tag, "etiqueta");
        validateRequiredText(tag.getName(), "nombre");
        return tagDAO.update(tag);
    }

    public void delete(Long id) {
        tagDAO.delete(id);
    }
}

