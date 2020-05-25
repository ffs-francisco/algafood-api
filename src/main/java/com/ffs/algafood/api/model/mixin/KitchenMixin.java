package com.ffs.algafood.api.model.mixin;

import com.ffs.algafood.domain.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author francisco
 */
public abstract class KitchenMixin implements Serializable {

    private Long id;

    private String name;

    @JsonIgnore
    private List<Restaurant> restaurants;
}
