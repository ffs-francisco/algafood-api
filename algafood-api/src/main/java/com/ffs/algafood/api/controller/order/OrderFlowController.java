package com.ffs.algafood.api.controller.order;

import com.ffs.algafood.domain.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/orders/{orderCode}")
public class OrderFlowController {

    @Autowired
    private OrderService orderService;

    @PutMapping("/confimation")
    @ResponseStatus(NO_CONTENT)
    public void confirm(@PathVariable final String orderCode) {
        orderService.confirm(orderCode);
    }

    @PutMapping("/cancelation")
    @ResponseStatus(NO_CONTENT)
    public void cancel(@PathVariable final String orderCode) {
        orderService.cancel(orderCode);
    }

    @PutMapping("/delivered")
    @ResponseStatus(NO_CONTENT)
    public void delivered(@PathVariable final String orderCode) {
        orderService.delivered(orderCode);
    }
}
