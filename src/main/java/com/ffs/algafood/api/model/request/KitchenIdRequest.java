package com.ffs.algafood.api.model.request;

import com.ffs.algafood.domain.model.Kitchen;
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
public class KitchenIdRequest {

    @NotNull
    private Long id;

    public Kitchen toModel() {
        return new ModelMapper().map(this, Kitchen.class);
    }
}
