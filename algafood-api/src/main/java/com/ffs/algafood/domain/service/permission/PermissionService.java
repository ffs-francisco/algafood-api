package com.ffs.algafood.domain.service.permission;

import com.ffs.algafood.domain.exception.PermissionNotFoundException;
import com.ffs.algafood.domain.model.permission.Permission;
import com.ffs.algafood.domain.repository.permission.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author francisco
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission findById(final Long permissionId) throws PermissionNotFoundException {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new PermissionNotFoundException("id", permissionId));
    }
}
