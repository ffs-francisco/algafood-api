package com.ffs.algafood.api.controller;

import com.ffs.algafood.api.model.request.payment.method.PaymentMethodRequest;
import com.ffs.algafood.api.model.response.payment.method.PaymentMethodResponse;
import com.ffs.algafood.domain.service.restaurant.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.springframework.http.HttpStatus.*;

/**
 * @author francisco
 */
@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentService;

    @GetMapping
    @ResponseStatus(OK)
    public ResponseEntity<List<PaymentMethodResponse>> listAll(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        final var latestUpdated = paymentService.findLatestUpdatedDate();
        final var eTag = (latestUpdated == null) ? "0" : String.valueOf(latestUpdated.toEpochSecond());
        if (request.checkNotModified(eTag)) {
            return null;
        }

        final var payments = PaymentMethodResponse.fromList(paymentService.findAll());
        return ResponseEntity.ok()
                .eTag(eTag)
                .cacheControl(CacheControl.maxAge(10, SECONDS))
                .body(payments);
    }

    @GetMapping("/{paymentMethodId}")
    @ResponseStatus(OK)
    public ResponseEntity<PaymentMethodResponse> getById(@PathVariable final Long paymentMethodId) {
        final var payment = PaymentMethodResponse.from(paymentService.findById(paymentMethodId));

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, SECONDS).cachePublic())
                .body(payment);
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

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable final Long paymentMethodId) {
        paymentService.delete(paymentMethodId);
    }
}
