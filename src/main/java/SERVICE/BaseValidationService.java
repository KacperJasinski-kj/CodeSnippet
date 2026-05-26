package SERVICE;


import EXCEPTION.ValidationException;

/**
 * Clase base para reutilizar validaciones comunes en los servicios.
 */
public abstract class BaseValidationService {

    /**
     * Comprueba que un texto no esté vacío.
     *
     * @param value texto a validar
     * @param fieldName nombre del campo para mostrarlo en el error
     */
    protected void validateRequiredText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException("El campo " + fieldName + " es obligatorio.");
        }
    }

    /**
     * Comprueba que un objeto no sea null.
     *
     * @param value objeto a validar
     * @param fieldName nombre del campo para mostrarlo en el error
     */
    protected void validateRequiredObject(Object value, String fieldName) {
        if (value == null) {
            throw new ValidationException("El campo " + fieldName + " es obligatorio.");
        }
    }
}