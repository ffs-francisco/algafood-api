package com.ffs.algafood.domain.repository.restaurant;

import com.ffs.algafood.domain.model.restaurant.Product;
import com.ffs.algafood.domain.model.restaurant.Restaurant;
import com.ffs.algafood.domain.repository.CustomJpaRepository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author francisco
 */
public interface ProductRepository extends CustomJpaRepository<Product, Long> {

    public List<Product> findAllByRestaurant(final Restaurant restaurant);

    public Optional<Product> findByRestaurantAndId(final Restaurant findById, final Long productId);
}
