package Panthera.Services.Validators;

/**
 * Exception when a product name is not valid.
 *
 * @author Daan Rosbergen
 */
public class ProductNaamNotValidException extends Exception {
    public ProductNaamNotValidException() {
    }

    /**
     * @author Daan Rosbergen
     * @param message
     */
    public ProductNaamNotValidException(String message) {
        super(message);
    }

    /**
     * @author Daan Rosbergen
     * @param message
     * @param cause
     */
    public ProductNaamNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @author Daan Rosbergen
     * @param cause
     */
    public ProductNaamNotValidException(Throwable cause) {
        super(cause);
    }
}
