package com.ffs.api.domain.exception;

import com.ffs.api.domain.model.Kitchen;

/**
 *
 * @author francisco
 */
public class KitchenNotFoundException extends EntityNotFoundException {

    public KitchenNotFoundException(String reason) {
        super(reason);
    }

    public KitchenNotFoundException(Long kitchenId) {
        super(kitchenId, Kitchen.class);
    }
}
