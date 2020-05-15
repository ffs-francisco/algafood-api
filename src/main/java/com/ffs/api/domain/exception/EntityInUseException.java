package com.ffs.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


/**
 *
 * @author francisco
 */
public class EntityInUseException extends ResponseStatusException {

    public EntityInUseException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public EntityInUseException(final String reason) {
        this(HttpStatus.CONFLICT, reason);
    }
}
