package com.ffs.algafood.api.controller.restaurant;

import com.ffs.algafood.api.model.request.restaurant.ProductImageRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
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
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/image")
public class RestaurantProductImageController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(OK)
    public void update(
            @PathVariable final Long restaurantId,
            @PathVariable final Long productId,
            @Valid ProductImageRequest imageRequest
    ) {
        final var imageName = UUID.randomUUID().toString() + "_" + imageRequest.getFile().getOriginalFilename();
        final var path = Path.of("/home/francisco/catalog", imageName);

        System.out.println(imageRequest.getDescription());
        System.out.println(imageName);
        System.out.println(path);

        try {
            imageRequest.getFile().transferTo(path);
        } catch (IOException | IllegalStateException ex) {
            Logger.getLogger(RestaurantProductImageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
