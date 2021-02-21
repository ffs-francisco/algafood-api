package com.ffs.algafood.infrastructor.service.storage.exception;

/**
 * @author francisco
 */
public class EmailException extends RuntimeException {

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
