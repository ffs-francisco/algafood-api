package com.ffs.algafood.api.controller.restaurant.product;

import com.ffs.algafood.api.model.request.restaurant.ProductRequest;
import com.ffs.algafood.api.model.response.restaurant.ProductResponse;
import com.ffs.algafood.domain.service.restaurant.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public List<ProductResponse> listAll(
            @PathVariable final Long restaurantId,
            @RequestParam(required = false) final boolean includeInactives
    ) {
        return ProductResponse.fromList(productService.findAllByRestaurant(restaurantId, includeInactives));
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
