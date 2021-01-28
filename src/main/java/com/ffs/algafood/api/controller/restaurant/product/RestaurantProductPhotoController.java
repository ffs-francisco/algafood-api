package com.ffs.algafood.api.controller.restaurant.product;

import com.ffs.algafood.api.model.request.restaurant.ProductPhotoRequest;
import com.ffs.algafood.api.model.request.restaurant.ProductPhotoResponse;
import com.ffs.algafood.domain.service.restaurant.product.ProductPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

/**
 * @author francisco
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    private final ProductPhotoService photoService;

    @PutMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(OK)
    public ProductPhotoResponse update(
            @PathVariable final Long restaurantId,
            @PathVariable final Long productId,
            @Valid ProductPhotoRequest photoRequest
    ) throws IOException {
        return ProductPhotoResponse.fromModel(this.photoService.save(restaurantId, productId, photoRequest.getPhoto()));
    }
}
