package com.ffs.algafood.api.model.response.order;

import com.ffs.algafood.api.model.response.payment.method.PaymentMethodResponse;
import com.ffs.algafood.api.model.response.restaurant.AddressResponse;
import com.ffs.algafood.api.model.response.user.UserResponse;
import com.ffs.algafood.domain.model.order.Order;
import com.ffs.algafood.domain.model.order.StatusOrderEnum;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

/**
 *
 * @author francisco
 */
@Setter
@Getter
public class OrderResponse {

    private Long id;

    private BigDecimal subTotal;
    private BigDecimal shippingFee;
    private BigDecimal amount;

    private StatusOrderEnum status;
    private PaymentMethodResponse paymentMethod;

    private OffsetDateTime dateRegister;
    private OffsetDateTime dateConfirmation;
    private OffsetDateTime dateCancellation;
    private OffsetDateTime dateDelivery;

    private UserResponse customer;
    private RestaurantResponse restaurant;
    private AddressResponse addressDelivery;

    private List<OrderItemResponse> itens;

    public static OrderResponse from(final Order order) {
        final var orderResponse = new ModelMapper().map(order, OrderResponse.class);
        orderResponse.setAddressDelivery(AddressResponse.from(order.getAddressDelivery()));

        return orderResponse;
    }

    public static List<OrderResponse> fromList(final List<Order> orders) {
        return orders.stream()
                .map(OrderResponse::from)
                .collect(Collectors.toList());
    }

}
