package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.PaymentMethod;

/**
 *
 * @author francisco
 */
public class PaymentMethodNotFoundException extends EntityNotFoundException {

    public PaymentMethodNotFoundException(String fieldName, Object value) {
        super(PaymentMethod.class, fieldName, value);
    }
}
