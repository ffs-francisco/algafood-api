package com.ffs.algafood.api.model.request.order;

import com.ffs.algafood.api.model.request.restaurant.AddressRequest;
import com.ffs.algafood.domain.model.order.Order;
import com.ffs.algafood.domain.model.order.OrderItem;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 *
 * @author francisco
 */
@Setter
@Getter
public class OrderRequest {

    @Valid
    @NotNull
    private RestaurantIdRequest restaurant;

    @Valid
    @NotNull
    private PaymentMethodIdRequest paymentMethod;

    @Valid
    @NotNull
    private AddressRequest addressDelivery;

    @Valid
    @NotNull
    @Size(min = 1)
    private List<OrderItemRequest> itens;

    public Order toModel() {
        final var mapper = new ModelMapper();
        mapper.createTypeMap(OrderItemRequest.class, OrderItem.class)
                .addMappings(map -> map.skip(OrderItem::setId));

        return mapper.map(this, Order.class);
    }

}
