package com.ffs.algafood.domain.repository;

import com.ffs.algafood.domain.model.Restaurant;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author francisco
 */
public interface RestaurantRepositoryCustom {

    List<Restaurant> find(String name, BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal);

    List<Restaurant> findWithFreeShippingAndNameSimilar(String name);
}
