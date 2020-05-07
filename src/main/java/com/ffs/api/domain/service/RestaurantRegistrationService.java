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
    var kitchenId = restaurant.getKitchen().getId();

    if (kitchenRepository.findById(kitchenId) == null) {
      throw new EntityNotFoundException(
              String.format("Não existe cadastro de conzinnha com código %d", kitchenId));
    }

    return restaurantRepository.save(restaurant);
  }

}
