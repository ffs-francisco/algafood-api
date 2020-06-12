package com.ffs.algafood.api.model.response.payment.method;

import com.ffs.algafood.domain.model.PaymentMethod;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class PaymentMethodResponse {

    private Long id;
    private String description;

    public static PaymentMethodResponse from(PaymentMethod paymentMethod) {
        return new ModelMapper().map(paymentMethod, PaymentMethodResponse.class);
    }

    public static List<PaymentMethodResponse> fromList(Collection<PaymentMethod> paymentMethods) {
        return paymentMethods.stream()
                .map(PaymentMethodResponse::from)
                .collect(Collectors.toList());
    }
}
