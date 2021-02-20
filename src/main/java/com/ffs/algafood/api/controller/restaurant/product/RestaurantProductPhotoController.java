package com.ffs.algafood.api.controller.restaurant.product;

import com.ffs.algafood.api.model.request.restaurant.ProductPhotoRequest;
import com.ffs.algafood.api.model.request.restaurant.ProductPhotoResponse;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.service.restaurant.product.ProductPhotoService;
import com.ffs.algafood.domain.service.storage.StoragePhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

/**
 * @author francisco
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    private final ProductPhotoService photoService;
    private final StoragePhotoService storageService;

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

    @GetMapping
    public ResponseEntity<?> findResourceByRestaurantAndProduct(
            @PathVariable final Long restaurantId,
            @PathVariable final Long productId,
            @RequestHeader(name = "accept") final String acceptHeader
    ) throws HttpMediaTypeNotAcceptableException {
        final var mediaTypeAccept = MediaType.parseMediaTypes(acceptHeader);

        try {
            final var photo = this.photoService.findByRestaurantAndProduct(restaurantId, productId);
            final var mediaTypePhoto = MediaType.parseMediaType(photo.getContentType());

            this.mediaTypeCompatibilityChecker(mediaTypeAccept, mediaTypePhoto);
            final var recoverPhoto = this.storageService.recover(photo.getFileName());

            if (recoverPhoto.hasUrl()) {
                return ResponseEntity.status(FOUND)
                        .header(LOCATION, recoverPhoto.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(mediaTypePhoto)
                        .body(new InputStreamResource(recoverPhoto.getInputStream()));
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void deleteResourceByRestaurantAndProduct(
            @PathVariable final Long restaurantId,
            @PathVariable final Long productId
    ) {
        this.photoService.deleteByRestaurantAndProduct(restaurantId, productId);
    }

    private void mediaTypeCompatibilityChecker(
            final List<MediaType> mediaTypeAccept,
            final MediaType mediaTypePhoto
    ) throws HttpMediaTypeNotAcceptableException {
        final var compatibility = mediaTypeAccept.stream()
                .anyMatch(mediaType -> mediaType.isCompatibleWith(mediaTypePhoto));

        if (!compatibility) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypeAccept);
        }
    }
}
