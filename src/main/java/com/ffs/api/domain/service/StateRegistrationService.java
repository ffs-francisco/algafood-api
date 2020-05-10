package com.ffs.api.domain.service;

import com.ffs.api.domain.exception.EntityInUseException;
import com.ffs.api.domain.exception.EntityNotFoundException;
import com.ffs.api.domain.model.State;
import com.ffs.api.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public void delete(final Long stateId) throws EntityInUseException, EntityNotFoundException {
        try {
            stateRepository.deleteById(stateId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Não existe um cadastro de estado com código %d", stateId));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Estado de código %d não pode ser removido, pois está em uso", stateId));
        }
    }
}
