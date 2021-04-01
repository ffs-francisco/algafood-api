package com.ffs.algafood.domain.repository.restaurant;

import com.ffs.algafood.domain.model.restaurant.Restaurant;
import com.ffs.algafood.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author francisco
 */
@Repository
public interface RestaurantRepository extends
        CustomJpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant>, RestaurantRepositoryCustom {

    int countByKitchenId(Long kitchenId);

    @Query("FROM Restaurant R JOIN FETCH R.kitchen")
    List<Restaurant> findAll();

    Optional<Restaurant> findFirstByNameContainingAndKitchenId(String name, Long kitchenId);

    List<Restaurant> findTop2ByNameContainingAndKitchenId(String name, Long kitchenId);

    List<Restaurant> findByLikeNameAndKitchenId(@Param("name") String name, @Param("kitchenId") Long kitchenId);
}
