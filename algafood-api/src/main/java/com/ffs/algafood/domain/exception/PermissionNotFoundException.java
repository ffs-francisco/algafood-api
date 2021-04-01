package com.ffs.algafood.domain.exception;

import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.permission.Permission;

/**
 *
 * @author francisco
 */
public class PermissionNotFoundException extends EntityNotFoundException {

    public PermissionNotFoundException(String fieldName, Object value) {
        super(Permission.class, fieldName, value);
    }
}
