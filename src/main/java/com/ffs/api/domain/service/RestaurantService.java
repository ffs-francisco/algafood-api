package com.ffs.api.domain.service;

import com.ffs.api.domain.exception.EntityNotFoundException;
import com.ffs.api.domain.exception.RestaurantNotFoundException;
import com.ffs.api.domain.model.Restaurant;
import com.ffs.api.domain.repository.RestaurantRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenService kitchenService;

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant findById(Long restaurantId) throws RestaurantNotFoundException {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

    @Transactional
    public Restaurant save(final Restaurant restaurant) throws EntityNotFoundException {
        final var kitchen = kitchenService.findById(restaurant.getKitchen().getId());

        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }
}
