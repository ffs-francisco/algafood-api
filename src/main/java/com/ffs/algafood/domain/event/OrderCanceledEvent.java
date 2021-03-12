package com.ffs.algafood.domain.event;

import com.ffs.algafood.domain.model.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCanceledEvent {

    private final Order order;
}
