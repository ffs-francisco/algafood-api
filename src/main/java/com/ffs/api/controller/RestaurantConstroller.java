package com.ffs.api.controller;

import com.ffs.api.domain.exception.EntityNotFoundException;
import com.ffs.api.domain.model.Restaurant;
import com.ffs.api.domain.repository.RestaurantRepository;
import com.ffs.api.domain.service.RestaurantRegistrationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/restaurants")
public class RestaurantConstroller {

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private RestaurantRegistrationService restaurantRegistrationService;

  @GetMapping
  public List<Restaurant> listAll() {
    return restaurantRepository.findAll();
  }

  @GetMapping("/{resturantId}")
  public ResponseEntity<Restaurant> getById(@PathVariable Long resturantId) {
    var restaurant = restaurantRepository.findById(resturantId);
    if (restaurant != null) {
      return ResponseEntity.ok(restaurant);
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<?> add(@RequestBody final Restaurant restaurant) {
    try {
      return ResponseEntity.status(HttpStatus.CREATED)
              .body(restaurantRegistrationService.save(restaurant));
    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
