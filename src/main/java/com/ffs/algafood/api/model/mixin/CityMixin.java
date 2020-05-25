package com.ffs.algafood.api.model.mixin;

import com.ffs.algafood.domain.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author francisco
 */
public abstract class CityMixin {

    private Long id;

    private String name;

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private State state;
}
