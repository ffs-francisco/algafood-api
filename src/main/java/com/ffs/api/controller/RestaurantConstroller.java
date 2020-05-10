package com.ffs.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffs.api.domain.exception.EntityNotFoundException;
import com.ffs.api.domain.model.Restaurant;
import com.ffs.api.domain.repository.RestaurantRepository;
import com.ffs.api.domain.service.RestaurantRegistrationService;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getById(@PathVariable Long restaurantId) {
        var restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(restaurant.get());
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

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable final Long restaurantId, @RequestBody final Restaurant restaurant) {
        var restaurantSaved = restaurantRepository.findById(restaurantId);

        try {
            if (restaurantSaved.isPresent()) {
                BeanUtils.copyProperties(restaurant, restaurantSaved.get(), "id");

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(restaurantRegistrationService.save(restaurantSaved.get()));
            }

            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable final Long restaurantId, @RequestBody Map<String, Object> fields) {
        var restaurantSaved = restaurantRepository.findById(restaurantId);
        if (restaurantSaved.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        merge(fields, restaurantSaved.get());
        return this.update(restaurantId, restaurantSaved.get());
    }

    private void merge(Map<String, Object> dataOrigin, Restaurant restaurantDestination) {
        Restaurant restaurantOrigin = new ObjectMapper().convertValue(dataOrigin, Restaurant.class);

        dataOrigin.forEach((namePropertie, valPropertie) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, namePropertie);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, restaurantOrigin);
            ReflectionUtils.setField(field, restaurantDestination, newValue);
        });
    }
}
