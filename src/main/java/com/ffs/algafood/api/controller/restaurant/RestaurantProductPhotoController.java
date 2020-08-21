package com.ffs.algafood.api.controller.restaurant;

import com.ffs.algafood.api.model.request.restaurant.ProductPhotoRequest;
import com.ffs.algafood.api.model.request.restaurant.ProductPhotoResponse;
import com.ffs.algafood.domain.service.restaurant.ProductPhotoService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

/**
 *
 * @author francisco
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    private final ProductPhotoService photoService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(OK)
    public ProductPhotoResponse update(
            @PathVariable final Long restaurantId,
            @PathVariable final Long productId,
            @Valid ProductPhotoRequest photoRequest
    ) {
        return ProductPhotoResponse.fromModel(
                this.photoService.save(restaurantId, productId, photoRequest.getDescription(), photoRequest.getFile())
        );
    }
}
