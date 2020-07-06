package com.ffs.algafood.domain.service;

import com.ffs.algafood.domain.exception.KitchenNotFoundException;
import com.ffs.algafood.domain.exception.base.EntityInUseException;
import com.ffs.algafood.domain.model.Kitchen;
import com.ffs.algafood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new KitchenNotFoundException("id", kitchenId));
    }

    public Page<Kitchen> findAll(final Pageable pageable) {
        return kitchenRepository.findAll(pageable);
    }

    @Transactional
    public Kitchen save(final Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Transactional
    public void deleteById(final Long kitchenId) throws EntityInUseException, KitchenNotFoundException {
        try {
            kitchenRepository.deleteById(kitchenId);
            kitchenRepository.flush();
        } catch (EmptyResultDataAccessException ex) {
            throw new KitchenNotFoundException("id", kitchenId);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(kitchenId, Kitchen.class);
        }
    }
}
