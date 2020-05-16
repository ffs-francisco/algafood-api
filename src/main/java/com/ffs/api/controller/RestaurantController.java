package com.ffs.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffs.api.domain.exception.BusinessException;
import com.ffs.api.domain.exception.EntityNotFoundException;
import com.ffs.api.domain.model.Restaurant;
import com.ffs.api.domain.service.RestaurantService;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    @ResponseStatus(OK)
    public List<Restaurant> listAll() {
        return restaurantService.findAll();
    }

    @GetMapping("/{restaurantId}")
    @ResponseStatus(OK)
    public Restaurant getById(@PathVariable Long restaurantId) {
        return restaurantService.findById(restaurantId);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Restaurant add(@RequestBody final Restaurant restaurant) {
        try {
            return restaurantService.save(restaurant);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}")
    @ResponseStatus(OK)
    public Restaurant update(@PathVariable final Long restaurantId, @RequestBody final Restaurant restaurant) {
        var restaurantSaved = restaurantService.findById(restaurantId);

        try {
            BeanUtils.copyProperties(restaurant, restaurantSaved,
                    "id", "formPayments", "address", "dateRegister", "products");
            return restaurantService.save(restaurantSaved);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PatchMapping("/{restaurantId}")
    @ResponseStatus(OK)
    public Restaurant update(@PathVariable final Long restaurantId, @RequestBody Map<String, Object> fields) {
        var restaurantSaved = restaurantService.findById(restaurantId);

        merge(fields, restaurantSaved);
        return this.update(restaurantId, restaurantSaved);
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
