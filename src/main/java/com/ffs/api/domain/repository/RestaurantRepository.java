package com.ffs.api.domain.repository;

import com.ffs.api.domain.model.Restaurant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francisco
 */
@Repository
public interface RestaurantRepository extends
        CustomJpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant>, RestaurantRepositoryCustom {

    int countByKitchenId(Long kitchenId);

    Optional<Restaurant> findFirstByNameContainingAndKitchenId(String name, Long kitchenId);

    List<Restaurant> findTop2ByNameContainingAndKitchenId(String name, Long kitchenId);

    //@Query("FROM Restaurant WHERE name LIKE %:name% AND kitchen.id = :kitchenId")
    List<Restaurant> findByLikeNameAndKitchenId(@Param("name") String name, @Param("kitchenId") Long kitchenId);
}
