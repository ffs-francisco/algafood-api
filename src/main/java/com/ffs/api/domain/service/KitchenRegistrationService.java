package com.ffs.api.domain.service;

import com.ffs.api.domain.model.Kitchen;
import com.ffs.api.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author francisco
 */
@Service
public class KitchenRegistrationService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen save(final Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }
}
