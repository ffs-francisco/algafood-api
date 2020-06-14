package com.ffs.algafood.api.controller.order;

import com.ffs.algafood.domain.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/orders/{orderId}")
public class OrderFlowController {

    @Autowired
    private OrderService orderService;

    @PutMapping("/confimation")
    @ResponseStatus(NO_CONTENT)
    public void confirm(@PathVariable final Long orderId) {
        orderService.confirm(orderId);
    }

    @PutMapping("/cancelation")
    @ResponseStatus(NO_CONTENT)
    public void cancel(@PathVariable final Long orderId) {
        orderService.cancel(orderId);
    }

    @PutMapping("/delivered")
    @ResponseStatus(NO_CONTENT)
    public void delivered(@PathVariable final Long orderId) {
        orderService.delivered(orderId);
    }
}
