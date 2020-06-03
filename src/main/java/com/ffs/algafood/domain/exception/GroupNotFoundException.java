package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.Group;

/**
 *
 * @author francisco
 */
public class GroupNotFoundException extends EntityNotFoundException {

    public GroupNotFoundException(String fieldName, Object value) {
        super(Group.class, fieldName, value);
    }
}
