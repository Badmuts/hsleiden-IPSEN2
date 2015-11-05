package Panthera.Services.Validators;

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
