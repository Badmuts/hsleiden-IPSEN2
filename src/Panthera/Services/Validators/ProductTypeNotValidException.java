package Panthera.Services.Validators;

/**
 * Exception when there is no valid product type.
 *
 * @author Daan Rosbergen
 */
public class ProductTypeNotValidException extends Exception {
    public ProductTypeNotValidException() {
    }

    public ProductTypeNotValidException(String message) {
        super(message);
    }

    public ProductTypeNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductTypeNotValidException(Throwable cause) {
        super(cause);
    }
}
