package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.FormPayment;

/**
 *
 * @author francisco
 */
public class FormPaymentNotFoundException extends EntityNotFoundException {

    public FormPaymentNotFoundException(String fieldName, Object value) {
        super(FormPayment.class, fieldName, value);
    }
}
