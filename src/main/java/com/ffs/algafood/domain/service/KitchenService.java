package com.ffs.algafood.domain.service;

import com.ffs.algafood.domain.exception.base.EntityInUseException;
import com.ffs.algafood.domain.exception.KitchenNotFoundException;
import com.ffs.algafood.domain.model.Kitchen;
import com.ffs.algafood.domain.repository.KitchenRepository;
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

    public Kitchen findById(final Long kitchenId) throws KitchenNotFoundException {
        return kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new KitchenNotFoundException(kitchenId));
    }

    public List<Kitchen> findAll() {
        return kitchenRepository.findAll();
    }

    public Kitchen save(final Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public void deleteById(final Long kitchenId) throws EntityInUseException, KitchenNotFoundException {
        try {
            kitchenRepository.deleteById(kitchenId);
        } catch (EmptyResultDataAccessException ex) {
            throw new KitchenNotFoundException(kitchenId);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(kitchenId, Kitchen.class);
        }
    }
}
