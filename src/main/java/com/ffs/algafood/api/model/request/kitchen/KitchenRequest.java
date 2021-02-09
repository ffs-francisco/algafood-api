package com.ffs.algafood.api.model.request.kitchen;

import com.ffs.algafood.domain.model.Kitchen;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class KitchenRequest implements Serializable{

    @NotBlank
    private String name;

    public Kitchen toModel() {
        return new ModelMapper().map(this, Kitchen.class);
    }

    public void copyPropertiesTo(Kitchen kitchen) {
        new ModelMapper().map(this, kitchen);
    }
}
