package com.ffs.algafood.core.validation.annotation;

import com.ffs.algafood.core.validation.annotation.validator.ZeroValueIncludeDescriptionValidator;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author francisco
 */
@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {ZeroValueIncludeDescriptionValidator.class})
public @interface ZeroValueIncludeDescription {

    String message() default "{api.validation.constraints.ZeroValueIncludeDescription.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public String fieldValue();

    public String fieldDescription();

    public String requiredDescription();
}
