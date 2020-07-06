package com.ffs.algafood.api.model.response.order;

import com.ffs.algafood.domain.model.order.Order;
import com.ffs.algafood.domain.model.order.StatusOrderEnum;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

/**
 *
 * @author francisco
 */
@Setter
@Getter
public class OrderSummaryResponse {

    private String code;

    private BigDecimal subTotal;
    private BigDecimal shippingFee;
    private BigDecimal amount;

    private StatusOrderEnum status;

    private OffsetDateTime dateRegister;

//    private UserResponse customer;
    private String nameCustomer;
    private RestaurantResponse restaurant;

    public static OrderSummaryResponse from(Order order) {
        return new ModelMapper().map(order, OrderSummaryResponse.class);
    }

    public static List<OrderSummaryResponse> fromList(List<Order> orders) {
        return orders.stream()
                .map(OrderSummaryResponse::from)
                .collect(Collectors.toList());
    }

    public static Page<OrderSummaryResponse> fromPage(Page<Order> page) {
        return page.map(OrderSummaryResponse::from);
    }
}
