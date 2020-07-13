package com.ffs.algafood.infrastructor.service.projection.exception;

/**
 *
 * @author francisco
 */
public class ProjectionException extends RuntimeException {

    public ProjectionException(String message) {
        super(message);
    }

    public ProjectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
