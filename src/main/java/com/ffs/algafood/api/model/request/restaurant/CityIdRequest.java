package com.ffs.algafood.api.model.request.restaurant;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
