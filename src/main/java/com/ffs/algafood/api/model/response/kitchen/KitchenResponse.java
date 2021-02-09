package com.ffs.algafood.api.model.response.kitchen;

import com.ffs.algafood.domain.model.Kitchen;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class KitchenResponse implements Serializable {

    private Long id;
    private String name;

    public static KitchenResponse from(Kitchen kitchen) {
        return new ModelMapper().map(kitchen, KitchenResponse.class);
    }

    public static List<KitchenResponse> fromList(List<Kitchen> kitchens) {
        return kitchens.stream()
                .map(KitchenResponse::from)
                .collect(Collectors.toList());
    }

    public static Page<KitchenResponse> fromPage(Page<Kitchen> page) {
        return page.map(KitchenResponse::from);
    }
}
