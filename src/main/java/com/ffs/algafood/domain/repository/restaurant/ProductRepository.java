package com.ffs.algafood.domain.repository.restaurant;

import com.ffs.algafood.domain.model.restaurant.Product;
import com.ffs.algafood.domain.model.restaurant.Restaurant;
import com.ffs.algafood.domain.repository.CustomJpaRepository;
import java.util.List;

/**
 *
 * @author francisco
 */
public interface ProductRepository extends CustomJpaRepository<Product, Long> {

    public List<Product> findAllByRestaurant(Restaurant restaurant);
}
