package io.chrismajor.wlt.exception;

/**
 * Exception to indicate a product wasn't found
 */
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

}
