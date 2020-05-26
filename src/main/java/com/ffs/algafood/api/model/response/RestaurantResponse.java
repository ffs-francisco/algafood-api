package com.ffs.algafood.api.model.response;

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
    private KitchenResponse kitchen;

    public static RestaurantResponse from(final Restaurant restaurant) {
        return new ModelMapper().map(restaurant, RestaurantResponse.class);
    }

    public static List<RestaurantResponse> fromList(final List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> from(restaurant))
                .collect(Collectors.toList());
    }
}
