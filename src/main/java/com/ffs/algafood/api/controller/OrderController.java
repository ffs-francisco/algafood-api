package com.ffs.algafood.api.controller;

import com.ffs.algafood.api.model.response.order.OrderResponse;
import com.ffs.algafood.domain.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @ResponseStatus(OK)
    public List<OrderResponse> listAll() {
        return OrderResponse.fromList(orderService.findAll());
    }

    @GetMapping("/{orderId}")
    @ResponseStatus(OK)
    public OrderResponse getById(@PathVariable final Long orderId) {
        return OrderResponse.from(orderService.findById(orderId));
    }
}
