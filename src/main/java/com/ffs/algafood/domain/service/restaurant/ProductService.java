package com.ffs.algafood.domain.service.restaurant;

import com.ffs.algafood.domain.exception.RestaurantNotFoundException;
import com.ffs.algafood.domain.model.restaurant.Product;
import com.ffs.algafood.domain.repository.restaurant.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
