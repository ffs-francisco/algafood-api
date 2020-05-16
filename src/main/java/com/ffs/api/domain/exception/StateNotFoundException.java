package com.ffs.api.domain.exception;

import com.ffs.api.domain.model.State;

/**
 *
 * @author francisco
 */
public class StateNotFoundException extends EntityNotFoundException {

    public StateNotFoundException(Long stateId) {
        super(stateId, State.class);
    }
}
