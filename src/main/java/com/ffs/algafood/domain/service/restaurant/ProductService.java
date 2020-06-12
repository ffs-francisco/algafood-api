package com.ffs.algafood.domain.service.restaurant;

import com.ffs.algafood.domain.exception.ProductNotFoundException;
import com.ffs.algafood.domain.exception.RestaurantNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.model.restaurant.Product;
import com.ffs.algafood.domain.repository.restaurant.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestaurantService restaurantService;

    public List<Product> findAllByRestaurant(final Long restaurantId) throws RestaurantNotFoundException {
        return productRepository.findAllByRestaurant(restaurantService.findById(restaurantId));
    }

    public Product findAByRestaurant(final Long restaurantId, final Long productId) throws ProductNotFoundException {
        return productRepository.findByRestaurantAndId(restaurantService.findById(restaurantId), productId)
                .orElseThrow(() -> new ProductNotFoundException("id", restaurantId));
    }

    @Transactional
    public Product save(final Product product) throws BusinessException {
        try {
            restaurantService.findById(product.getRestaurant().getId());
        } catch (RestaurantNotFoundException ex) {
            throw new BusinessException(ex.getMessage(), ex);
        }

        return productRepository.save(product);
    }
}
