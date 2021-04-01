package com.ffs.algafood.api.controller.restaurant;

import com.ffs.algafood.api.model.response.payment.method.PaymentMethodResponse;
import com.ffs.algafood.domain.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
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

    @PutMapping("/{paymentMethodId}")
    @ResponseStatus(NO_CONTENT)
    public void link(@PathVariable final Long restaurantId, @PathVariable final Long paymentMethodId) {
        restaurantService.linkPaymentMethod(restaurantId, paymentMethodId);
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(NO_CONTENT)
    public void unlink(@PathVariable final Long restaurantId, @PathVariable final Long paymentMethodId) {
        restaurantService.unlinkPaymentMethod(restaurantId, paymentMethodId);
    }
}
