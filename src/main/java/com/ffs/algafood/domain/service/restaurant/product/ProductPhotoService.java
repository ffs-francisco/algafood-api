package com.ffs.algafood.domain.service.restaurant.product;

import com.ffs.algafood.domain.model.restaurant.ProductPhoto;
import com.ffs.algafood.domain.repository.restaurant.ProductRepository;
import com.ffs.algafood.domain.service.storage.StoragePhotoService;
import com.ffs.algafood.domain.service.storage.StoragePhotoService.NewPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author francisco
 */
@Service
@RequiredArgsConstructor
public class ProductPhotoService {

    private final ProductRepository productRepository;

    private final StoragePhotoService storageService;
    private final ProductService productService;

    @Transactional
    public ProductPhoto save(final Long restaurantId, final Long productId, final NewPhoto newPhoto) {
        final var product = this.productService.findAByRestaurant(restaurantId, productId);
        this.productRepository.findPhotoById(restaurantId, productId).ifPresent(this.productRepository::delete);

        final var photo = new ProductPhoto();
        photo.setProduct(product);
        photo.setDescription(newPhoto.getDescription());
        photo.setSize(newPhoto.getSize());
        photo.setContentType(newPhoto.getContentType());
        photo.setFileName(newPhoto.getFileName());

        final var productPhotoUpdated = this.productRepository.save(photo);
        this.productRepository.flush();

        this.storageService.store(newPhoto);
        return productPhotoUpdated;
    }
}
