package com.ffs.algafood.api.model.response.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 *
 * @author francisco
 */
@Setter
@Getter
class OrderItemResponse {

    private Long id;

    private Integer quantity;
    private BigDecimal priceUnit;
    private BigDecimal priceAmount;
    private String observation;

    private Long productId;
    private String productName;
}
