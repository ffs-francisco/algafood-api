package com.ffs.algafood.api.controller.restaurant;

import com.ffs.algafood.api.model.request.restaurant.RestaurantRequest;
import com.ffs.algafood.api.model.response.restaurant.RestaurantResponse;
import com.ffs.algafood.api.ultil.ApiUpdateUtils;
import com.ffs.algafood.domain.exception.RestaurantNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.service.restaurant.RestaurantService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "fields", paramType = "query", type = "string", value = "Property names to filter in the response, separated by comma")
    })
    @GetMapping
    @ResponseStatus(OK)
    public List<RestaurantResponse> listAll(@RequestParam(required = false) final String projection) {
        return RestaurantResponse.fromList(restaurantService.findAll());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "fields", paramType = "query", type = "string", value = "Property names to filter in the response, separated by comma")
    })
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
