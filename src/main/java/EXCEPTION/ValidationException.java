package EXCEPTION;

/**
 * Excepción personalizada para errores de validación.
 *
 * Se usa cuando el usuario introduce datos incorrectos,
 * por ejemplo campos vacíos o valores no válidos.
 */
public class ValidationException extends RuntimeException {

    /**
     * Constructor que recibe el mensaje del error.
     *
     * @param message mensaje que explica el problema
     */
    public ValidationException(String message) {
        super(message);
    }
}

