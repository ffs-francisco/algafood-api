package com.ffs.algafood.api.model.response.restaurant;

import com.ffs.algafood.api.model.response.kitchen.KitchenResponse;
import com.ffs.algafood.domain.model.Restaurant;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class RestaurantResponse implements Serializable {

    private Long id;
    private String name;
    private BigDecimal shippingFee;
    private Boolean active;
    private KitchenResponse kitchen;

    public static RestaurantResponse from(final Restaurant restaurant) {
        return new ModelMapper().map(restaurant, RestaurantResponse.class);
    }

    public static List<RestaurantResponse> fromList(final List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantResponse::from)
                .collect(Collectors.toList());
    }
}
