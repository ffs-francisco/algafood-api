package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.City;

/**
 *
 * @author francisco
 */
public class CityNotFoundException extends EntityNotFoundException {

    public CityNotFoundException(String fieldName, Object value) {
        super(City.class, fieldName, value);
    }
}
