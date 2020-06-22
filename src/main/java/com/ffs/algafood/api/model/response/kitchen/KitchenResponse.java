package com.ffs.algafood.api.model.response.kitchen;

import com.fasterxml.jackson.annotation.JsonView;
import com.ffs.algafood.api.model.request.view.RestaurantView;
import com.ffs.algafood.domain.model.Kitchen;
import java.io.Serializable;
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
public class KitchenResponse implements Serializable {

    @JsonView(RestaurantView.Sumary.class)
    private Long id;

    @JsonView(RestaurantView.Sumary.class)
    private String name;

    public static KitchenResponse from(Kitchen kitchen) {
        return new ModelMapper().map(kitchen, KitchenResponse.class);
    }

    public static List<KitchenResponse> fromList(List<Kitchen> kitchens) {
        return kitchens.stream()
                .map(KitchenResponse::from)
                .collect(Collectors.toList());
    }
}
