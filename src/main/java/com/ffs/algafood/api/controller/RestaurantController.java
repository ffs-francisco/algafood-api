package com.ffs.algafood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffs.algafood.api.model.request.restaurant.RestaurantRequest;
import com.ffs.algafood.api.model.response.restaurant.RestaurantResponse;
import com.ffs.algafood.core.validation.ValidationException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.service.RestaurantService;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
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

    @Autowired
    private SmartValidator smartValidator;

    @GetMapping
    @ResponseStatus(OK)
    public List<RestaurantResponse> listAll() {
        return RestaurantResponse.fromList(restaurantService.findAll());
    }

    @GetMapping("/{restaurantId}")
    @ResponseStatus(OK)
    public RestaurantResponse getById(@PathVariable Long restaurantId) {
        return RestaurantResponse.from(restaurantService.findById(restaurantId));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public RestaurantResponse add(@RequestBody @Valid final RestaurantRequest restaurant) {
        try {
            return RestaurantResponse.from(restaurantService.save(restaurant.toModel()));
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restaurantId}")
    @ResponseStatus(OK)
    public RestaurantResponse update(@PathVariable final Long restaurantId, @RequestBody @Valid final RestaurantRequest restaurantRequest) {
        var restaurant = restaurantService.findById(restaurantId);

        try {
            restaurantRequest.copyPropertiesTo(restaurant);
            return RestaurantResponse.from(restaurantService.save(restaurant));
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PatchMapping("/{restaurantId}")
    @ResponseStatus(OK)
    public RestaurantResponse update(@PathVariable final Long restaurantId, @RequestBody Map<String, Object> fieldsRequest, HttpServletRequest request) {
        var restaurantRequestSaved = new ModelMapper()
                .map(restaurantService.findById(restaurantId), RestaurantRequest.class);

        this.merge(fieldsRequest, restaurantRequestSaved, request);
        this.validate(restaurantRequestSaved, "restaurant");
        return this.update(restaurantId, restaurantRequestSaved);
    }

    private void merge(Map<String, Object> dataOrigin, Object dataDestination, HttpServletRequest request) throws HttpMessageNotReadableException {
        try {
            var objectMapper = new ObjectMapper();
            objectMapper.configure(FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, true);

            var objectOrigin = objectMapper.convertValue(dataOrigin, dataDestination.getClass());
            dataOrigin.forEach((namePropertie, valPropertie) -> {
                Field field = ReflectionUtils.findField(dataDestination.getClass(), namePropertie);
                field.setAccessible(true);

                Object newValue = ReflectionUtils.getField(field, objectOrigin);
                ReflectionUtils.setField(field, dataDestination, newValue);
            });
        } catch (IllegalArgumentException ex) {
            var rootCouse = ExceptionUtils.getRootCause(ex);
            var servletRequest = new ServletServerHttpRequest(request);

            throw new HttpMessageNotReadableException(ex.getMessage(), rootCouse, servletRequest);
        }
    }

    private void validate(RestaurantRequest restaurant, String objectName) throws ValidationException {
        var bindingResult = new BeanPropertyBindingResult(restaurant, objectName);

        this.smartValidator.validate(restaurant, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
    }
}
