package com.ffs.api.infrastructor.repository;

import com.ffs.api.domain.model.Permission;
import com.ffs.api.domain.repository.PermissionRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Component
public class PermissionRepositoryImpl implements PermissionRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Permission save(final Permission permission) {
        return this.manager.merge(permission);
    }

    @Override
    @Transactional
    public void delete(final Permission permission) {
        this.manager.remove(this.findById(permission.getId()));
    }

    @Override
    public List<Permission> findAll() {
        return this.manager.createQuery("FROM Permission", Permission.class).getResultList();
    }

    @Override
    public Permission findById(Long id) {
        return this.manager.find(Permission.class, id);
    }
}
