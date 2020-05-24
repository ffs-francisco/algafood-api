package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.State;

/**
 *
 * @author francisco
 */
public class StateNotFoundException extends EntityNotFoundException {

    public StateNotFoundException(String fieldName, Object value) {
        super(State.class, fieldName, value);
    }
}
