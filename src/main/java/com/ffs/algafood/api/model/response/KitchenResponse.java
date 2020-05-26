package com.ffs.algafood.api.model.response;

import com.ffs.algafood.domain.model.Kitchen;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

/**
 *
 * @author francisco
 */
@Getter
@Setter
class KitchenResponse implements Serializable {

    private Long id;
    private String name;

    static KitchenResponse from(Kitchen kitchen) {
        return new ModelMapper().map(kitchen, KitchenResponse.class);
    }
}
