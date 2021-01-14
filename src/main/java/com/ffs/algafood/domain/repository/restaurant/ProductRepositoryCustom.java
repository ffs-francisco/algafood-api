package com.ffs.algafood.domain.repository.restaurant;

import com.ffs.algafood.domain.model.restaurant.ProductPhoto;

/**
 * @author francisco
 */
public interface ProductRepositoryCustom {

    ProductPhoto save(final ProductPhoto productPhoto);

    void delete(final ProductPhoto productPhoto);
}
