package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.restaurant.ProductPhoto;

/**
 * @author francisco
 */
public class ProductPhotoNotFoundException extends EntityNotFoundException {

    public ProductPhotoNotFoundException(String fieldName, Object value) {
        super(ProductPhoto.class, fieldName, value);
    }
}
