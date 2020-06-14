package com.ffs.algafood.api.model.response.order;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

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
