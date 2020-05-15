package com.ffs.api.domain.service;

import com.ffs.api.domain.exception.EntityInUseException;
import com.ffs.api.domain.exception.EntityNotFoundException;
import com.ffs.api.domain.model.Kitchen;
import com.ffs.api.domain.repository.KitchenRepository;
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
public class KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen findById(final Long kitchenId) throws EntityNotFoundException {
        return kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_NOT_FOUND, kitchenId)));
    }

    public List<Kitchen> findAll() {
        return kitchenRepository.findAll();
    }

    public Kitchen save(final Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public void deleteById(final Long kitchenId) throws EntityInUseException, EntityNotFoundException {
        try {
            kitchenRepository.deleteById(kitchenId);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(String.format(MSG_NOT_FOUND, kitchenId));
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(String.format(MSG_CONFLICT, kitchenId));
        }
    }

    private final String MSG_CONFLICT = "Cozinha de código %d não pode ser removida, pois já está em uso";
    private final String MSG_NOT_FOUND = "Não existe um cadastro de cozinha com código %d";
}
