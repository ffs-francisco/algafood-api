package com.ffs.algafood.api.model.request.payment.method;

import com.ffs.algafood.domain.model.restaurant.PaymentMethod;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class PaymentMethodRequest {

    @NotBlank
    private String description;

    public PaymentMethod toModel() {
        return new ModelMapper().map(this, PaymentMethod.class);
    }

    public void copyPropertiesTo(PaymentMethod paymentMethod) {
        new ModelMapper().map(this, paymentMethod);
    }
}
