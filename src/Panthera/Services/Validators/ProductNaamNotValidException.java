package Panthera.Services.Validators;

public class ProductNaamNotValidException extends Exception {
    public ProductNaamNotValidException() {
    }

    public ProductNaamNotValidException(String message) {
        super(message);
    }

    public ProductNaamNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNaamNotValidException(Throwable cause) {
        super(cause);
    }
}
