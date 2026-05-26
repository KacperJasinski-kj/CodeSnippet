package SERVICE;

import DAO.LanguageDAO;
import MODEL.Language;
import java.util.List;

/**
 * Servicio de lenguajes de programación.
 */
public class LanguageService extends BaseValidationService {

    private final LanguageDAO languageDAO = new LanguageDAO();

    public void save(Language language) {
        validateRequiredObject(language, "lenguaje");
        validateRequiredText(language.getName(), "nombre");
        languageDAO.save(language);
    }

    public Language findById(Long id) {
        return languageDAO.findById(id);
    }

    public List<Language> findAll() {
        return languageDAO.findAll();
    }

    public Language update(Language language) {
        validateRequiredObject(language, "lenguaje");
        validateRequiredText(language.getName(), "nombre");
        return languageDAO.update(language);
    }

    public void delete(Long id) {
        languageDAO.delete(id);
    }
}
