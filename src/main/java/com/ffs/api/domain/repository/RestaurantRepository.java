package com.ffs.api.domain.repository;

import com.ffs.api.domain.model.Restaurant;
import java.util.List;

/**
 *
 * @author francisco
 */
public interface RestaurantRepository {

    List<Restaurant> findAll();

    Restaurant findById(Long id);

    Restaurant save(Restaurant restaurant);

    void delete(Restaurant restaurant);
}
