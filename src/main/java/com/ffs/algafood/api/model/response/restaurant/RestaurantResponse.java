package com.ffs.algafood.api.model.response.restaurant;

import com.fasterxml.jackson.annotation.JsonView;
import com.ffs.algafood.api.model.request.view.RestaurantView;
import com.ffs.algafood.api.model.response.kitchen.KitchenResponse;
import com.ffs.algafood.domain.model.restaurant.Restaurant;
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

    @JsonView({RestaurantView.Sumary.class, RestaurantView.JustName.class})
    private Long id;

    @JsonView({RestaurantView.Sumary.class, RestaurantView.JustName.class})
    private String name;

    @JsonView(RestaurantView.Sumary.class)
    private BigDecimal shippingFee;

    private Boolean active;
    private Boolean open;
    private AddressResponse address;

    @JsonView(RestaurantView.Sumary.class)
    private KitchenResponse kitchen;

    public static RestaurantResponse from(final Restaurant restaurant) {
        final var restaurantResponse = new ModelMapper().map(restaurant, RestaurantResponse.class);
        restaurantResponse.setAddress(AddressResponse.from(restaurant.getAddress()));

        return restaurantResponse;
    }

    public static List<RestaurantResponse> fromList(final List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantResponse::from)
                .collect(Collectors.toList());
    }
}
