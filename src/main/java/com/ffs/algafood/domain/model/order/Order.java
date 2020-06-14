package com.ffs.algafood.domain.model.order;

import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.model.Address;
import com.ffs.algafood.domain.model.User;
import com.ffs.algafood.domain.model.restaurant.PaymentMethod;
import com.ffs.algafood.domain.model.restaurant.Restaurant;
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
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import static com.ffs.algafood.domain.model.order.StatusOrderEnum.*;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author francisco
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "\"order\"") // The name is necessary. Becoause "GROUP" is an SQL rezeverd word
public class Order implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private BigDecimal subTotal;
    private BigDecimal shippingFee;
    private BigDecimal amount;

    @Enumerated(STRING)
    private StatusOrderEnum status = CREATED;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PaymentMethod paymentMethod;

    @CreationTimestamp
    private OffsetDateTime dateRegister;
    private OffsetDateTime dateConfirmation;
    private OffsetDateTime dateCancellation;
    private OffsetDateTime dateDelivery;

    @ManyToOne
    @JoinColumn(name = "customer_user_id", nullable = false)
    private User customer;

    @Embedded
    private Address addressDelivery;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "order", cascade = ALL)
    private List<OrderItem> itens = new ArrayList<>();

    public void confirm() {
        setStatus(CONFIRMED);
        setDateConfirmation(OffsetDateTime.now());
    }

    public void cancel() {
        setStatus(CANCELED);
        setDateCancellation(OffsetDateTime.now());
    }

    public void delivered() {
        setStatus(DELIVERED);
        setDateDelivery(OffsetDateTime.now());
    }

    private void setStatus(final StatusOrderEnum status) {
        if (!getStatus().isValidStatusChanging(status)) {
            throw new BusinessException(
                    String.format("Order %d status can no longer be %s", getId(), status.getDescription()));
        }

        this.status = status;
    }
}
