package com.ffs.algafood.domain.service.restaurant;

import com.ffs.algafood.domain.model.restaurant.ProductPhoto;
import com.ffs.algafood.domain.repository.restaurant.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author francisco
 */
@Service
@RequiredArgsConstructor
public class ProductPhotoService {

    private final ProductRepository productRepository;

    private final ProductService productService;

    @Transactional
    public ProductPhoto save(
            final Long restaurantId, final Long productId,
            final String description, final MultipartFile filePhoto
    ) {
        final var product = this.productService.findAByRestaurant(restaurantId, productId);

        this.productRepository.findPhotoById(restaurantId, productId).ifPresent(this.productRepository::delete);

        final var photo = new ProductPhoto();
        photo.setProduct(product);
        photo.setDescription(description);
        photo.setSize(filePhoto.getSize());
        photo.setContentType(filePhoto.getContentType());
        photo.setFileName(filePhoto.getOriginalFilename());

        return this.productRepository.save(photo);
    }
}
