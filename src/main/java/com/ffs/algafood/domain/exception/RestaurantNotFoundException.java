package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.model.Restaurant;

/**
 *
 * @author francisco
 */
public class RestaurantNotFoundException extends EntityNotFoundException {

    public RestaurantNotFoundException(String reason) {
        super(reason);
    }

    public RestaurantNotFoundException(Long restaurantId) {
        super(restaurantId, Restaurant.class);
    }
}
