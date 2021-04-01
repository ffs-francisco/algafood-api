package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.restaurant.Product;

/**
 *
 * @author francisco
 */
public class ProductNotFoundException extends EntityNotFoundException {

    public ProductNotFoundException(String fieldName, Object value) {
        super(Product.class, fieldName, value);
    }
}
