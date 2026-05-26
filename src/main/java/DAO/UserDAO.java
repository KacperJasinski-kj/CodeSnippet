package DAO;

import MODEL.User;

/**
 * DAO específico para la entidad User.
 *
 * Hereda de GenericDAO para tener CRUD completo sin repetir código.
 */
public class UserDAO extends GenericDAO<User> {

    /**
     * Constructor que indica al DAO genérico qué entidad debe manejar.
     */
    public UserDAO() {
        super(User.class);
    }
}

