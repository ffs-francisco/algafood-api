package com.ffs.api.domain.repository;

import com.ffs.api.domain.model.Permission;
import java.util.List;

/**
 *
 * @author francisco
 */
public interface PermissionRepository {

    List<Permission> findAll();

    Permission findById(Long id);

    Permission save(final Permission permission);

    void delete(final Permission permission);
}
