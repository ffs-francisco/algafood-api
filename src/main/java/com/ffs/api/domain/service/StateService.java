package com.ffs.api.domain.service;

import com.ffs.api.domain.exception.EntityInUseException;
import com.ffs.api.domain.exception.EntityNotFoundException;
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

    public State findById(final Long stateId) {
        return stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_NOT_FOUND, stateId)));
    }

    public List<State> findAll() {
        return stateRepository.findAll();
    }

    public State save(final State state) {
        return stateRepository.save(state);
    }

    public void delete(final Long stateId) throws EntityInUseException, EntityNotFoundException {
        try {
            stateRepository.deleteById(stateId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format(MSG_NOT_FOUND, stateId));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(MSG_CONFLICT, stateId));
        }
    }

    private final String MSG_NOT_FOUND = "Não existe um cadastro de estado com código %d";
    private final String MSG_CONFLICT = "Estado de código %d não pode ser removido, pois está em uso";
}
