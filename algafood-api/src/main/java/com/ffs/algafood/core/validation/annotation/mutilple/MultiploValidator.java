package com.ffs.algafood.core.validation.annotation.mutilple;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 *
 * @author francisco
 */
class MultiploValidator implements ConstraintValidator<Multiple, Number> {

    private BigDecimal multipleNumber;

    @Override
    public void initialize(Multiple constraintAnnotation) {
        this.multipleNumber = BigDecimal.valueOf(constraintAnnotation.number());
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        var decimalValue = BigDecimal.valueOf(value.doubleValue());
        var remainder = decimalValue.remainder(this.multipleNumber);

        return BigDecimal.ZERO.compareTo(remainder) == 0;
    }
}
