package com.ffs.algafood.api.model.response.restaurant;

import com.ffs.algafood.api.model.response.kitchen.KitchenResponse;
import com.ffs.algafood.domain.model.Address;
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

    private Long id;
    private String name;
    private BigDecimal shippingFee;
    private Boolean active;
    private Boolean open;
    private AddressResponse address;
    private KitchenResponse kitchen;

    public static RestaurantResponse from(final Restaurant restaurant) {
        final var modelMapepr = new ModelMapper();
        final var mapping = modelMapepr.createTypeMap(Address.class, AddressResponse.class);
        mapping.<String>addMapping(
                (addressSrc) -> addressSrc.getCity().getState().getName(),
                (addressDest, value) -> addressDest.getCity().setState(value)
        );

        return modelMapepr.map(restaurant, RestaurantResponse.class);
    }

    public static List<RestaurantResponse> fromList(final List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantResponse::from)
                .collect(Collectors.toList());
    }
}
