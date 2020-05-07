package com.ffs.api.controller;

import com.ffs.api.domain.model.Restaurant;
import com.ffs.api.domain.repository.RestaurantRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
