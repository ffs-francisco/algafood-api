package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.model.City;

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
