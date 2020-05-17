package com.ffs.api.domain.exception;

import com.ffs.api.domain.model.City;

/**
 *
 * @author francisco
 */
public class CityNotFoundException extends EntityNotFoundException {

    public CityNotFoundException(String reason) {
        super(reason);
    }

    public CityNotFoundException(Long cityId) {
        super(cityId, City.class);
    }
}
