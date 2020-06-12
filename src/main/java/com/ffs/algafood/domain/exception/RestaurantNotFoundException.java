package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.restaurant.Restaurant;

/**
 *
 * @author francisco
 */
public class RestaurantNotFoundException extends EntityNotFoundException {

    public RestaurantNotFoundException(String fieldName, Object value) {
        super(Restaurant.class, fieldName, value);
    }
}
