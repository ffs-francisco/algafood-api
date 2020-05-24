package com.ffs.algafood.domain.exception.base;

import java.lang.reflect.Field;
import lombok.Getter;
import org.springframework.util.ReflectionUtils;

/**
 *
 * @author francisco
 */
@Getter
public class EntityNotFoundException extends BusinessException {

    private final Class<?> entity;
    private final Field field;
    private final Object value;

    public EntityNotFoundException(Class<?> entity, Field field, Object value, String reason, Throwable cause) {
        super(reason, cause);
        this.entity = entity;
        this.field = field;
        this.value = value;
    }

    public EntityNotFoundException(Class<?> entity, Field field, Object value, String reason) {
        super(reason);
        this.entity = entity;
        this.field = field;
        this.value = value;
    }

    public EntityNotFoundException(Class<?> entity, String fieldName, Object value) {
        super(String.format("There is no %s register with %s equals %s.", entity.getSimpleName(), fieldName, value.toString()));
        this.entity = entity;
        this.field = findField(entity, fieldName);
        this.value = value;
    }

    private Field findField(Class<?> entity, String fieldName) {
        return ReflectionUtils.findField(entity, fieldName);
    }
}
