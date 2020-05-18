package com.ffs.algafood.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 *
 * @author francisco
 */
@ResponseStatus(NOT_FOUND)
public abstract class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String reason) {
        super(reason);
    }

    public EntityNotFoundException(Long id, Class<?> entity) {
        this(String.format("There is no %s register with conde %d.", entity.getSimpleName(), id));
    }
}
