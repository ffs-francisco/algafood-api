package com.ffs.api.domain.service;

import com.ffs.api.domain.model.State;
import com.ffs.api.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author francisco
 */
@Service
public class StateRegistrationService {

    @Autowired
    private StateRepository stateRepository;

    public State save(final State state) {
        return stateRepository.save(state);
    }
}
