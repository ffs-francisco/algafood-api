package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.Kitchen;

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
