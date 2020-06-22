package com.ffs.algafood.api.controller.restaurant;

import com.ffs.algafood.api.model.request.restaurant.RestaurantRequest;
import com.ffs.algafood.api.model.request.view.RestaurantView;
import com.ffs.algafood.api.model.response.restaurant.RestaurantResponse;
import com.ffs.algafood.api.ultil.ApiUpdateUtils;
import com.ffs.algafood.domain.exception.RestaurantNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.service.restaurant.RestaurantService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

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
    private ApiUpdateUtils updateUtils;

    @GetMapping
    @ResponseStatus(OK)
    public MappingJacksonValue listAll(@RequestParam(required = false) final String projection) {
        final var restaurants = RestaurantResponse.fromList(restaurantService.findAll());
        final var restaurantsMapper = new MappingJacksonValue(restaurants);

        restaurantsMapper.setSerializationView(RestaurantView.Sumary.class);
        if ("just-name".equals(projection)) {
            restaurantsMapper.setSerializationView(RestaurantView.JustName.class);
        } else if ("complete".equals(projection)) {
            restaurantsMapper.setSerializationView(null);
        }

        return restaurantsMapper;
    }

    @GetMapping("/{restaurantId}")
    @ResponseStatus(OK)
    public RestaurantResponse getById(@PathVariable Long restaurantId) {
        return RestaurantResponse.from(restaurantService.findById(restaurantId));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public RestaurantResponse add(@RequestBody @Valid final RestaurantRequest restaurant) {
        return RestaurantResponse.from(restaurantService.save(restaurant.toModel()));
    }

    @PutMapping("/{restaurantId}")
    @ResponseStatus(OK)
    public RestaurantResponse update(@PathVariable final Long restaurantId, @RequestBody @Valid final RestaurantRequest restaurantRequest) {
        var restaurant = restaurantService.findById(restaurantId);

        restaurantRequest.copyPropertiesTo(restaurant);
        return RestaurantResponse.from(restaurantService.save(restaurant));
    }

    @PatchMapping("/{restaurantId}")
    @ResponseStatus(OK)
    public RestaurantResponse update(@PathVariable final Long restaurantId, @RequestBody Map<String, Object> fieldsRequest, HttpServletRequest request) {
        var restaurantRequestSaved = new ModelMapper()
                .map(restaurantService.findById(restaurantId), RestaurantRequest.class);

        updateUtils.merge(fieldsRequest, restaurantRequestSaved, request);
        updateUtils.validate(restaurantRequestSaved, "restaurant");
        return this.update(restaurantId, restaurantRequestSaved);
    }

    @PutMapping("/{restaurantId}/active")
    @ResponseStatus(NO_CONTENT)
    public void active(@PathVariable final Long restaurantId) {
        restaurantService.activate(restaurantId);
    }

    @DeleteMapping("/{restaurantId}/inactive")
    @ResponseStatus(NO_CONTENT)
    public void inactive(@PathVariable final Long restaurantId) {
        restaurantService.inactivate(restaurantId);
    }

    @PutMapping("/activations")
    @ResponseStatus(NO_CONTENT)
    public void activeMany(@RequestBody final List<Long> restaurantIds) {
        try {
            restaurantService.activate(restaurantIds);
        } catch (RestaurantNotFoundException ex) {
            throw new BusinessException(ex.getMessage());
        }
    }

    @DeleteMapping("/inactivations")
    @ResponseStatus(NO_CONTENT)
    public void inactiveMany(@RequestBody final List<Long> restaurantIds) {
        try {
            restaurantService.inactivate(restaurantIds);
        } catch (RestaurantNotFoundException ex) {
            throw new BusinessException(ex.getMessage());
        }
    }

    @PutMapping("/{restaurantId}/opening")
    @ResponseStatus(NO_CONTENT)
    public void opening(@PathVariable final Long restaurantId) {
        restaurantService.opening(restaurantId);
    }

    @PutMapping("/{restaurantId}/closing")
    @ResponseStatus(NO_CONTENT)
    public void closing(@PathVariable final Long restaurantId) {
        restaurantService.closing(restaurantId);
    }
}
