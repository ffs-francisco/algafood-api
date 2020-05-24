package com.ffs.algafood.core.validation.annotation.validator;

import com.ffs.algafood.core.validation.annotation.Multiple;
import java.math.BigDecimal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author francisco
 */
public class MultiploValidator implements ConstraintValidator<Multiple, Number> {

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
