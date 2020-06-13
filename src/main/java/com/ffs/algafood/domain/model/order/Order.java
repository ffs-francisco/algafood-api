package com.ffs.algafood.domain.model.order;

import com.ffs.algafood.domain.model.Address;
import com.ffs.algafood.domain.model.restaurant.PaymentMethod;
import com.ffs.algafood.domain.model.restaurant.Restaurant;
import com.ffs.algafood.domain.model.User;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author francisco
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Order implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private BigDecimal subTotal;
    private BigDecimal shippingFee;
    private BigDecimal amount;

    @Enumerated(STRING)
    private StatusOrderEnum status;

    @Embedded
    private Address addressDelivery;

    @CreationTimestamp
    private OffsetDateTime dateRegister;
    private OffsetDateTime dateConfirmation;
    private OffsetDateTime dateCancellation;
    private OffsetDateTime dateDelivery;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PaymentMethod formPayment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_customer_id", nullable = false)
    private User customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> itens = new ArrayList<>();
}
