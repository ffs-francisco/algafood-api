package com.ffs.api.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

/**
 *
 * @author francisco
 */
@ResponseStatus(CONFLICT)
public class EntityInUseException extends RuntimeException {

    public EntityInUseException(String reason) {
        super(reason);
    }
}
