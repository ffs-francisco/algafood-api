package com.ffs.api.controller;

import com.ffs.api.domain.model.Kitchen;
import com.ffs.api.domain.model.Restaurant;
import com.ffs.api.domain.repository.KitchenRepository;
import com.ffs.api.domain.repository.RestaurantRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/kitchens/all-by-name")
    public List<Kitchen> kitchensByName(String name) {
        return kitchenRepository.findAllByNameContaining(name);
    }

    @GetMapping("/kitchens/by-name")
    public Optional<Kitchen> kitchenByName(String name) {
        return kitchenRepository.findByName(name);
    }

    @GetMapping("/restaurants/by-name")
    public List<Restaurant> restaurantByName(String name, Long kitchenId) {
        return restaurantRepository.findByLikeNameAndKitchenId(name, kitchenId);
    }
}
