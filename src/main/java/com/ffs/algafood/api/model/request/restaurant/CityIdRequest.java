package com.ffs.algafood.api.model.request.restaurant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author francisco
 */
@Getter
@Setter
class CityIdRequest {

    @Min(1)
    @NotNull
    private Long id;
}
