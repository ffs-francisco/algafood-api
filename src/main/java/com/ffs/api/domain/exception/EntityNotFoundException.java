package com.ffs.api.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 *
 * @author francisco
 */
@ResponseStatus(NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(final String message) {
        super(message);
    }
}
