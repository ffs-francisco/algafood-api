package com.ffs.algafood.api.controller.restaurant.user;

import com.ffs.algafood.api.model.response.user.UserResponse;
import com.ffs.algafood.domain.service.restaurant.RestaurantService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/restaurants/{restaurantId}/responsibles")
public class RestaurantUserResponsibleMethodController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    @ResponseStatus(OK)
    public List<UserResponse> listAll(@PathVariable final Long restaurantId) {
        return UserResponse.fromList(
                restaurantService.findById(restaurantId).getResponsibles()
        );
    }

    @PutMapping("/{userId}")
    @ResponseStatus(NO_CONTENT)
    public void associate(@PathVariable final Long restaurantId, @PathVariable final Long userId) {
        restaurantService.associateResponsible(restaurantId, userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(NO_CONTENT)
    public void unassociate(@PathVariable final Long restaurantId, @PathVariable final Long userId) {
        restaurantService.unassociateResponsible(restaurantId, userId);
    }
}
