package SERVICE;

import DAO.UserDAO;
import MODEL.User;
import java.util.List;

/**
 * Servicio de usuarios.
 *
 * Aquí se validan los datos antes de llamar al DAO.
 */
public class UserService extends BaseValidationService {

    private final UserDAO userDAO = new UserDAO();

    public void save(User user) {
        validateRequiredObject(user, "usuario");
        validateRequiredText(user.getName(), "nombre");
        validateRequiredText(user.getEmail(), "correo");
        userDAO.save(user);
    }

    public User findById(Long id) {
        return userDAO.findById(id);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User update(User user) {
        validateRequiredObject(user, "usuario");
        validateRequiredText(user.getName(), "nombre");
        validateRequiredText(user.getEmail(), "correo");
        return userDAO.update(user);
    }

    public void delete(Long id) {
        userDAO.delete(id);
    }
}

