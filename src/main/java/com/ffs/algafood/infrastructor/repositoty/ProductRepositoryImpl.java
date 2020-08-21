package com.ffs.algafood.infrastructor.repositoty;

import com.ffs.algafood.domain.model.restaurant.ProductPhoto;
import com.ffs.algafood.domain.repository.restaurant.ProductRepositoryCustom;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public ProductPhoto save(final ProductPhoto productPhoto) {
        return this.entityManager.merge(productPhoto);
    }
}
