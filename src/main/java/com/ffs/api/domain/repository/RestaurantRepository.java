package com.ffs.api.domain.repository;

import com.ffs.api.domain.model.Restaurant;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francisco
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByShippingFeeBetween(BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal);

    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchenId);

    Optional<Restaurant> findFirstByNameContainingAndKitchenId(String name, Long kitchenId);

    List<Restaurant> findTop2ByNameContainingAndKitchenId(String name, Long kitchenId);

    int countByKitchenId(Long kitchenId);
}
