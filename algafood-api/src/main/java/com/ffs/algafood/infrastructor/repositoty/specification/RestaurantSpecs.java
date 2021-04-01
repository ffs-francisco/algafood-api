package com.ffs.algafood.infrastructor.repositoty.specification;

import com.ffs.algafood.domain.model.restaurant.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

/**
 *
 * @author francisco
 */
public class RestaurantSpecs {

    public static Specification<Restaurant> whitFreeShipping() {
        return (root, query, builder)
                -> builder.equal(root.get("shippingFee"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> whitNameSimilar(String name) {
        return (root, query, builder)
                -> builder.like(root.get("name"), "%" + name + "%");
    }
}
