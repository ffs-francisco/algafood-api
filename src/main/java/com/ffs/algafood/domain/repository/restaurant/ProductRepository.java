package com.ffs.algafood.domain.repository.restaurant;

import com.ffs.algafood.domain.model.restaurant.Product;
import com.ffs.algafood.domain.model.restaurant.ProductPhoto;
import com.ffs.algafood.domain.model.restaurant.Restaurant;
import com.ffs.algafood.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author francisco
 */
public interface ProductRepository extends CustomJpaRepository<Product, Long>, ProductRepositoryCustom {

    Optional<Product> findByRestaurantAndId(final Restaurant findById, final Long productId);

    @Query("FROM Product P " +
            "WHERE (P.restaurant = :restaurant) AND (P.active IS TRUE) OR (:includeInactives IS TRUE AND P.active IS FALSE)")
    List<Product> findAllActiveByRestaurant(final Restaurant restaurant, final boolean includeInactives);

    @Query("SELECT PP FROM ProductPhoto PP JOIN PP.product P " +
            "WHERE P.restaurant.id = :restaurantId AND PP.product.id = :productId")
    Optional<ProductPhoto> findPhotoById(final Long restaurantId, final Long productId);
}
