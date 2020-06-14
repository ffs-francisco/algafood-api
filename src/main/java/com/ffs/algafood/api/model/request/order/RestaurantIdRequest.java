package com.ffs.algafood.api.model.request.order;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
