package com.ffs.algafood.api.controller.restaurant;

import com.ffs.algafood.api.model.request.restaurant.ProductRequest;
import com.ffs.algafood.api.model.response.restaurant.ProductResponse;
import com.ffs.algafood.domain.service.restaurant.ProductService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ResponseStatus(OK)
    public List<ProductResponse> listAll(@PathVariable final Long restaurantId) {
        return ProductResponse.fromList(productService.findAllByRestaurant(restaurantId));
    }

    @GetMapping("/{productId}")
    @ResponseStatus(OK)
    public ProductResponse getById(@PathVariable final Long restaurantId, @PathVariable final Long productId) {
        return ProductResponse.from(productService.findAByRestaurant(restaurantId, productId));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ProductResponse add(
            @PathVariable final Long restaurantId,
            @RequestBody @Valid final ProductRequest productRequest
    ) {
        return ProductResponse.from(productService.save(productRequest.toModel(restaurantId)));
    }

    @PutMapping("/{productId}")
    @ResponseStatus(OK)
    public ProductResponse update(
            @PathVariable final Long restaurantId,
            @PathVariable final Long productId,
            @RequestBody @Valid final ProductRequest productRequest
    ) {
        final var product = productService.findAByRestaurant(restaurantId, productId);

        productRequest.copyPropertiesTo(product);
        return ProductResponse.from(productService.save(product));
    }
}
