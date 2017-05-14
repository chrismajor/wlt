package io.chrismajor.wlt.exception;

/**
 * Exception to indicate an error occurred at the service level
 */
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }
}
