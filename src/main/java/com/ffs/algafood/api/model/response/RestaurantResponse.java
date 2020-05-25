package com.ffs.algafood.api.model.response;

import com.ffs.algafood.domain.model.Restaurant;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

/**
 *
 * @author francisco
 */
@Getter
@Builder
public class RestaurantResponse implements Serializable {

    private final Long id;
    private final String name;
    private final BigDecimal shippingFee;
    private final KitichenResponse kitichen;

    public static RestaurantResponse from(final Restaurant restaurant) {
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .shippingFee(restaurant.getShippingFee())
                .kitichen(KitichenResponse.from(restaurant.getKitchen()))
                .build();
    }

    public static List<RestaurantResponse> fromList(final List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> from(restaurant))
                .collect(Collectors.toList());
    }
}
