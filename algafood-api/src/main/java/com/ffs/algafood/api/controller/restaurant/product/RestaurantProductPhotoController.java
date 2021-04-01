package com.ffs.algafood.api.controller.restaurant.product;

import com.ffs.algafood.api.model.request.restaurant.ProductPhotoResponse;
import com.ffs.algafood.core.validation.annotation.FileContentType.FileContentType;
import com.ffs.algafood.core.validation.annotation.FileSize.FileSize;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.service.restaurant.product.ProductPhotoService;
import com.ffs.algafood.domain.service.storage.StoragePhotoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

import static com.ffs.algafood.domain.service.storage.StoragePhotoService.NewPhoto;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

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
            @NotBlank
            @RequestParam String description,

            @NotNull
            @RequestPart(required = true)
            @FileSize(max = "500KB")
            @FileContentType(allowed = {IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE}) MultipartFile file
    ) throws IOException {
        final var photo = NewPhoto.builder()
                .description(description)
                .size(file.getSize())
                .originalFileName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .inputStream(file.getInputStream())
                .build();

        return ProductPhotoResponse.fromModel(this.photoService.save(restaurantId, productId, photo));
    }

    @ApiOperation(value = "Get a photo of product", produces = "application/json, image/jpeg, image/png")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ProductPhotoResponse findByRestaurantAndProduct(
            @PathVariable final Long restaurantId,
            @PathVariable final Long productId
    ) {
        return ProductPhotoResponse.fromModel(this.photoService.findByRestaurantAndProduct(restaurantId, productId));
    }

    @GetMapping(produces = {IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE})
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
