package com.ffs.api.domain.repository;

import com.ffs.api.domain.model.Restaurant;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author francisco
 */
public interface RestaurantRepositoryCustom {

    List<Restaurant> find(String name, BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal);
}
