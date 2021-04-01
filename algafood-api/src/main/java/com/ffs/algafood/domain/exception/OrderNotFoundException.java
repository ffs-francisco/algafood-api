package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.order.Order;

/**
 *
 * @author francisco
 */
public class OrderNotFoundException extends EntityNotFoundException {

    public OrderNotFoundException(String fieldName, Object value) {
        super(Order.class, fieldName, value);
    }
}
