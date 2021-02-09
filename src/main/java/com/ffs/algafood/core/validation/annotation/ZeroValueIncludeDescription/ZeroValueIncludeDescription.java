package com.ffs.algafood.core.validation.annotation.ZeroValueIncludeDescription;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author francisco
 */
@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {ZeroValueIncludeDescriptionValidator.class})
public @interface ZeroValueIncludeDescription {

    String message() default "{ZeroValueIncludeDescription}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public String fieldValue();

    public String fieldDescription();

    public String requiredDescription();
}
