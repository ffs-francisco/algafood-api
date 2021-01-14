package com.ffs.algafood.infrastructor.repositoty;

import com.ffs.algafood.domain.model.restaurant.ProductPhoto;
import com.ffs.algafood.domain.repository.restaurant.ProductRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author francisco
 */
@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public ProductPhoto save(final ProductPhoto productPhoto) {
        return this.entityManager.merge(productPhoto);
    }

    @Override
    @Transactional
    public void delete(final ProductPhoto productPhoto) {
        this.entityManager.remove(productPhoto);
    }
}
