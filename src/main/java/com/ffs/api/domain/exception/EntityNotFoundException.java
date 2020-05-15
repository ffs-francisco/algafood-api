package com.ffs.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author francisco
 */
public class EntityNotFoundException extends ResponseStatusException {

    public EntityNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public EntityNotFoundException(String reason) {
        this(HttpStatus.NOT_FOUND, reason);
    }
}
