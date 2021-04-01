package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.Kitchen;

/**
 *
 * @author francisco
 */
public class KitchenNotFoundException extends EntityNotFoundException {

    public KitchenNotFoundException(String fieldName, Object value) {
        super(Kitchen.class, fieldName, value);
    }
}
