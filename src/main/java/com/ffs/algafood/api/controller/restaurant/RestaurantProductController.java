package com.ffs.algafood.api.controller.restaurant;

import com.ffs.algafood.api.model.response.restaurant.ProductResponse;
import com.ffs.algafood.domain.service.restaurant.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ResponseStatus(OK)
    public List<ProductResponse> listAll(@PathVariable final Long restaurantId) {
        return ProductResponse.fromList(productService.findAllByRestaurant(restaurantId));
    }
}
