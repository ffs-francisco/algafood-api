package com.ffs.api.domain.service;

import com.ffs.api.domain.exception.EntityInUseException;
import com.ffs.api.domain.exception.EntityNotFoundException;
import com.ffs.api.domain.exception.StateNotFoundException;
import com.ffs.api.domain.model.State;
import com.ffs.api.domain.repository.StateRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author francisco
 */
@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public List<State> findAll() {
        return stateRepository.findAll();
    }

    public State findById(final Long stateId) throws EntityNotFoundException {
        return stateRepository.findById(stateId)
                .orElseThrow(() -> new StateNotFoundException(stateId));
    }

    public State save(final State state) {
        return stateRepository.save(state);
    }

    public void delete(final Long stateId) throws EntityInUseException, EntityNotFoundException {
        try {
            stateRepository.deleteById(stateId);
        } catch (EmptyResultDataAccessException e) {
            throw new StateNotFoundException(stateId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(stateId, State.class);
        }
    }
}
