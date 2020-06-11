package com.ffs.algafood.api.controller;

import com.ffs.algafood.api.model.response.payment.method.PaymentMethodResponse;
import com.ffs.algafood.domain.service.RestaurantService;
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
@RequestMapping("/restaurants/{restaurantId}/payment-methods")
public class RestaurantPaymentMethodController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    @ResponseStatus(OK)
    public List<PaymentMethodResponse> listAll(@PathVariable final Long restaurantId) {
        return PaymentMethodResponse.fromList(
                restaurantService.findById(restaurantId).getPaymentMethods()
        );
    }
}
