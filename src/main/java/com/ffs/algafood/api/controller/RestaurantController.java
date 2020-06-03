package com.ffs.algafood.api.controller;

import com.ffs.algafood.api.model.request.restaurant.RestaurantRequest;
import com.ffs.algafood.api.model.response.restaurant.RestaurantResponse;
import com.ffs.algafood.api.ultil.ApiUpdateUtils;
import com.ffs.algafood.domain.service.RestaurantService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;
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
    private ApiUpdateUtils updateUtils;

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
}
