package com.ffs.api.domain.service;

import com.ffs.api.domain.exception.EntityNotFoundException;
import com.ffs.api.domain.model.Restaurant;
import com.ffs.api.domain.repository.KitchenRepository;
import com.ffs.api.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Service
public class RestaurantRegistrationService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    @Transactional
    public Restaurant save(final Restaurant restaurant) throws EntityNotFoundException {
        final var kitchenId = restaurant.getKitchen().getId();

        final var kitchen = kitchenRepository.findById(kitchenId);
        if (kitchen == null) {
            throw new EntityNotFoundException(
                    String.format("Não existe cadastro de conzinha com código %d", kitchenId));
        }

        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }

}
