package com.ffs.api.domain.exception;

import com.ffs.api.domain.model.Restaurant;

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
