package Panthera.Services.Validators;

/**
 * Exception when there is no valid product type.
 *
 * @author Daan Rosbergen
 */
public class ProductTypeNotValidException extends Exception {
    public ProductTypeNotValidException() {
    }

    /**
     * @author Daan Rosbergen
     * @param message
     */
    public ProductTypeNotValidException(String message) {
        super(message);
    }

    /**
     * @author Daan Rosbergen
     * @param message
     * @param cause
     */
    public ProductTypeNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @author Daan Rosbergen
     * @param cause
     */
    public ProductTypeNotValidException(Throwable cause) {
        super(cause);
    }
}
