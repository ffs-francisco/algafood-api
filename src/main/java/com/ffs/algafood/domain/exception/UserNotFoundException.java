package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.User;

/**
 *
 * @author francisco
 */
public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String fieldName, Object value) {
        super(User.class, fieldName, value);
    }
}
