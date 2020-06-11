package com.ffs.algafood.domain.exception.base;

import java.lang.reflect.Field;
import org.springframework.util.ReflectionUtils;

/**
 *
 * @author francisco
 */
public class AttributeInUseException extends BusinessException {

    private final Class<?> entity;
    private final Field field;
    private final Object fieldValue;

    public AttributeInUseException(Class<?> entity, String fieldName, Object fiealdValue) {
        super(String.format("Already exist a %s register with %s equals %s.", entity.getSimpleName(), fieldName, fiealdValue.toString()));

        this.entity = entity;
        this.field = findField(entity, fieldName);
        this.fieldValue = fiealdValue;
    }

    private Field findField(Class<?> entity, String fieldName) {
        return ReflectionUtils.findField(entity, fieldName);
    }
}
