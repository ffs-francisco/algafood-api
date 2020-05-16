package com.ffs.api.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

/**
 *
 * @author francisco
 */
@ResponseStatus(CONFLICT)
public class EntityInUseException extends BusinessException {

    public EntityInUseException(String reason) {
        super(reason);
    }

    public EntityInUseException(Long id, Class<?> entity) {
        this(String.format("The %s entity registration with code %d is already in use.", entity.getSimpleName(), id));
    }
}
