package com.ffs.algafood.api.controller;

import com.ffs.algafood.api.model.request.payment.method.PaymentMethodRequest;
import com.ffs.algafood.api.model.response.payment.method.PaymentMethodResponse;
import com.ffs.algafood.domain.service.PaymentMethodService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentService;

    @GetMapping
    @ResponseStatus(OK)
    public List<PaymentMethodResponse> listAll() {
        return PaymentMethodResponse.fromList(paymentService.findAll());
    }

    @GetMapping("/{paymentMethodId}")
    @ResponseStatus(OK)
    public PaymentMethodResponse getById(@PathVariable final Long paymentMethodId) {
        return PaymentMethodResponse.from(paymentService.findById(paymentMethodId));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public PaymentMethodResponse add(@RequestBody @Valid final PaymentMethodRequest paymentMethodRequest) {
        return PaymentMethodResponse.from(paymentService.save(paymentMethodRequest.toModel()));
    }

    @PutMapping("/{paymentMethodId}")
    @ResponseStatus(OK)
    public PaymentMethodResponse update(
            @PathVariable final Long paymentMethodId,
            @RequestBody @Valid final PaymentMethodRequest paymentMethodRequest
    ) {
        final var paymentMethodSaved = paymentService.findById(paymentMethodId);

        paymentMethodRequest.copyPropertiesTo(paymentMethodSaved);
        return PaymentMethodResponse.from(paymentService.save(paymentMethodSaved));
    }
}
