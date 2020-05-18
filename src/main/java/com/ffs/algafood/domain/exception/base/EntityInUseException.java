package com.ffs.algafood.domain.exception.base;

/**
 *
 * @author francisco
 */
public class EntityInUseException extends BusinessException {

    public EntityInUseException(String reason) {
        super(reason);
    }

    public EntityInUseException(Long id, Class<?> entity) {
        this(String.format("The %s entity registration with code %d is already in use.", entity.getSimpleName(), id));
    }
}
