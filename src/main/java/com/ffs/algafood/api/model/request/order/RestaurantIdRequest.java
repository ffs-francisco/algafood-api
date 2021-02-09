package com.ffs.algafood.api.model.request.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author francisco
 */
@Setter
@Getter
class RestaurantIdRequest {

    @Min(1)
    @NotNull
    private Long id;
}
