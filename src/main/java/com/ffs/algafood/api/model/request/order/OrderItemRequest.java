package com.ffs.algafood.api.model.request.order;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author francisco
 */
@Getter
@Setter
class OrderItemRequest {

    @NotNull
    @Min(1)
    private Long productId;

    @NotNull
    @Positive
    private Integer quantity;

    private String observation;
}
