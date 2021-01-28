package com.ffs.algafood.api.controller.restaurant.product;

import com.ffs.algafood.api.model.request.restaurant.ProductPhotoRequest;
import com.ffs.algafood.api.model.request.restaurant.ProductPhotoResponse;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.service.restaurant.product.ProductPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.*;

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
    public ProductPhotoResponse updateByRestaurantAndProduct(
            @PathVariable final Long restaurantId,
            @PathVariable final Long productId,
            @Valid ProductPhotoRequest photoRequest
    ) throws IOException {
        return ProductPhotoResponse.fromModel(this.photoService.save(restaurantId, productId, photoRequest.getPhoto()));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ProductPhotoResponse findByRestaurantAndProduct(
            @PathVariable final Long restaurantId,
            @PathVariable final Long productId
    ) {
        return ProductPhotoResponse.fromModel(this.photoService.findByRestaurantAndProduct(restaurantId, productId));
    }

    @GetMapping(produces = IMAGE_JPEG_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<InputStreamResource> findResourceByRestaurantAndProduct(
            @PathVariable final Long restaurantId,
            @PathVariable final Long productId
    ) {
        try {
            final var photoResource = this.photoService.findResourceByRestaurantAndProduct(restaurantId, productId);

            return ResponseEntity.ok()
                    .contentType(IMAGE_JPEG)
                    .body(new InputStreamResource(photoResource));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
