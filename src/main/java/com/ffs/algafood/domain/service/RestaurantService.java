package com.ffs.algafood.domain.service;

import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.exception.RestaurantNotFoundException;
import com.ffs.algafood.domain.model.Restaurant;
import com.ffs.algafood.domain.repository.RestaurantRepository;
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
                .orElseThrow(() -> new RestaurantNotFoundException("id", restaurantId));
    }

    @Transactional
    public Restaurant save(final Restaurant restaurant) throws EntityNotFoundException {
        final var kitchen = kitchenService.findById(restaurant.getKitchen().getId());

        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }
}
