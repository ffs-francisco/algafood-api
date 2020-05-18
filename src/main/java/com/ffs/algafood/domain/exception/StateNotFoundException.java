package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.model.State;

/**
 *
 * @author francisco
 */
public class StateNotFoundException extends EntityNotFoundException {

    public StateNotFoundException(String reason) {
        super(reason);
    }

    public StateNotFoundException(Long stateId) {
        super(stateId, State.class);
    }
}
