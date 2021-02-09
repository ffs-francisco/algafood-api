package com.ffs.algafood.api.model.request.restaurant;


import com.ffs.algafood.domain.model.Kitchen;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author francisco
 */
@Getter
@Setter
class KitchenIdRequest implements Serializable {

    @NotNull
    private Long id;

    public Kitchen toModel() {
        return new ModelMapper().map(this, Kitchen.class);
    }
}
