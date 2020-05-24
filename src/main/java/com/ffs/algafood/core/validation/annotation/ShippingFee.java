package com.ffs.algafood.core.validation.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author francisco
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@PositiveOrZero
public @interface ShippingFee {

    @OverridesAttribute(constraint = PositiveOrZero.class, name = "message")
    String message() default "{ShippingFee}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
