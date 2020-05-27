package com.ffs.algafood.api.model.request.restaurant;


import com.ffs.algafood.domain.model.Kitchen;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

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
