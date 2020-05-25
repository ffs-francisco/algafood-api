package com.ffs.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ffs.algafood.domain.model.Address;
import com.ffs.algafood.domain.model.FormPayment;
import com.ffs.algafood.domain.model.Kitchen;
import com.ffs.algafood.domain.model.Product;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 *
 * @author francisco
 */
public abstract class RestaurantMixin {

    private Long id;

    private String name;

    private BigDecimal shippingFee;

    @JsonIgnore
    private Address address;

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private Kitchen kitchen;

    @JsonIgnore
    private List<Product> products;

    @JsonIgnore
    private List<FormPayment> formPayments;

    @JsonIgnore
    private OffsetDateTime dateRegister;

    @JsonIgnore
    private OffsetDateTime dateUpdate;
}
