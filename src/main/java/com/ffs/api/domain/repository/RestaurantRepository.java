package com.ffs.api.domain.repository;

import com.ffs.api.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francisco
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
