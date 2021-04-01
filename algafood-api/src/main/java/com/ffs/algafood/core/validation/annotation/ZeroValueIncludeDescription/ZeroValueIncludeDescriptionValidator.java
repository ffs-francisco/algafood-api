package com.ffs.algafood.core.validation.annotation.ZeroValueIncludeDescription;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

/**
 *
 * @author francisco
 */
class ZeroValueIncludeDescriptionValidator implements ConstraintValidator<ZeroValueIncludeDescription, Object> {

    public String fieldValue;
    public String fieldDescription;
    public String requiredDescription;

    @Override
    public void initialize(ZeroValueIncludeDescription constraint) {
        this.fieldValue = constraint.fieldValue();
        this.fieldDescription = constraint.fieldDescription();
        this.requiredDescription = constraint.requiredDescription();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try {
            BigDecimal value = (BigDecimal) BeanUtils.getPropertyDescriptor(object.getClass(), fieldValue)
                    .getReadMethod().invoke(object);
            String description = (String) BeanUtils.getPropertyDescriptor(object.getClass(), fieldDescription)
                    .getReadMethod().invoke(object);

            if (value == null || description == null) {
                return true;
            } else if (BigDecimal.ZERO.compareTo(value) == 0) {
                return description.toLowerCase().contains(this.requiredDescription.toLowerCase());
            } else {
                return !description.toLowerCase().contains(this.requiredDescription.toLowerCase());
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | BeansException ex) {
            throw new ValidationException(ex);
        }
    }
}
