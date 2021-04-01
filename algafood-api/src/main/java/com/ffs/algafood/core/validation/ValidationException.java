package com.ffs.algafood.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

/**
 *
 * @author francisco
 */
@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {

    private final BindingResult bindingResult;
}
