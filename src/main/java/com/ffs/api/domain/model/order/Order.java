package com.ffs.api.domain.model.order;

import com.ffs.api.domain.model.Address;
import com.ffs.api.domain.model.FormPayment;
import com.ffs.api.domain.model.Restaurant;
import com.ffs.api.domain.model.User;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private LocalDateTime dateRegister;
    private LocalDateTime dateConfirmation;
    private LocalDateTime dateCancellation;
    private LocalDateTime dateDelivery;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FormPayment formPayment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_customer_id", nullable = false)
    private User customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> itens = new ArrayList<>();
}
