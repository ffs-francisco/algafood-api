package com.ffs.algafood.domain.exception.base;

/**
 *
 * @author francisco
 */
public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String reason) {
        super(reason);
    }

    public EntityNotFoundException(Long id, Class<?> entity) {
        this(String.format("There is no %s register with conde %d.", entity.getSimpleName(), id));
    }
}
