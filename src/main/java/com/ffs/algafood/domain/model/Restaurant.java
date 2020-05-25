package com.ffs.algafood.domain.model;

import com.ffs.algafood.core.validation.group.Groups;
import com.ffs.algafood.core.validation.annotation.Multiple;
import com.ffs.algafood.core.validation.annotation.ZeroValueIncludeDescription;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author francisco
 */
@ZeroValueIncludeDescription(
        fieldValue = "shippingFee",
        fieldDescription = "name",
        requiredDescription = "Frete Grátis"
)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Multiple(number = 5)
    @PositiveOrZero
    @Column(name = "shipping_fee", nullable = false)
    private BigDecimal shippingFee;

    @Embedded
    private Address address;

    @Valid
    @NotNull
    @ConvertGroup(from = Default.class, to = Groups.KitchenId.class)
    @ManyToOne
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurant_form_payment",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "form_payment_id"))
    private List<FormPayment> formPayments = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false)
    private OffsetDateTime dateRegister;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime dateUpdate;
}
