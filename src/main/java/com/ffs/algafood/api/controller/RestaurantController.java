package com.ffs.algafood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.Restaurant;
import com.ffs.algafood.domain.service.RestaurantService;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
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

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
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
    public Restaurant add(@RequestBody @Valid final Restaurant restaurant) {
        try {
            return restaurantService.save(restaurant);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restaurantId}")
    @ResponseStatus(OK)
    public Restaurant update(@PathVariable final Long restaurantId, @RequestBody @Valid final Restaurant restaurant) {
        var restaurantSaved = restaurantService.findById(restaurantId);

        try {
            BeanUtils.copyProperties(restaurant, restaurantSaved,
                    "id", "formPayments", "address", "dateRegister", "products");
            return restaurantService.save(restaurantSaved);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PatchMapping("/{restaurantId}")
    @ResponseStatus(OK)
    public Restaurant update(@PathVariable final Long restaurantId, @RequestBody Map<String, Object> fields, HttpServletRequest request) {
        var restaurantSaved = restaurantService.findById(restaurantId);

        merge(fields, restaurantSaved, request);
        return this.update(restaurantId, restaurantSaved);
    }

    private void merge(Map<String, Object> dataOrigin, Restaurant restaurantDestination, HttpServletRequest request) {
        try {

            var objectMapper = new ObjectMapper();
            objectMapper.configure(FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurant restaurantOrigin = objectMapper.convertValue(dataOrigin, Restaurant.class);
            dataOrigin.forEach((namePropertie, valPropertie) -> {
                Field field = ReflectionUtils.findField(Restaurant.class, namePropertie);
                field.setAccessible(true);

                Object newValue = ReflectionUtils.getField(field, restaurantOrigin);
                ReflectionUtils.setField(field, restaurantDestination, newValue);
            });
        } catch (IllegalArgumentException ex) {
            var rootCouse = ExceptionUtils.getRootCause(ex);
            var servletRequest = new ServletServerHttpRequest(request);

            throw new HttpMessageNotReadableException(ex.getMessage(), rootCouse, servletRequest);
        }
    }
}
