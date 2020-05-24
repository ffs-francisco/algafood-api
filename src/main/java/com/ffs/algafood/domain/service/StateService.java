package com.ffs.algafood.domain.service;

import com.ffs.algafood.domain.exception.base.EntityInUseException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.exception.StateNotFoundException;
import com.ffs.algafood.domain.model.State;
import com.ffs.algafood.domain.repository.StateRepository;
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
                .orElseThrow(() -> new StateNotFoundException("id", stateId));
    }

    public State save(final State state) {
        return stateRepository.save(state);
    }

    public void delete(final Long stateId) throws EntityInUseException, EntityNotFoundException {
        try {
            stateRepository.deleteById(stateId);
        } catch (EmptyResultDataAccessException e) {
            throw new StateNotFoundException("id", stateId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(stateId, State.class);
        }
    }
}
