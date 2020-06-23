package com.ffs.algafood.domain.repository.restaurant;

import com.ffs.algafood.domain.model.restaurant.Product;
import com.ffs.algafood.domain.model.restaurant.Restaurant;
import com.ffs.algafood.domain.repository.CustomJpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author francisco
 */
public interface ProductRepository extends CustomJpaRepository<Product, Long> {

    public Optional<Product> findByRestaurantAndId(final Restaurant findById, final Long productId);

    @Query("FROM Product P WHERE (P.restaurant = :restaurant) AND (P.active IS TRUE) OR (:includeInactives IS TRUE AND P.active IS FALSE)")
    public List<Product> findAllActiveByRestaurant(final Restaurant restaurant, final boolean includeInactives);
}
