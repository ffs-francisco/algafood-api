package com.ffs.algafood.api.model.request;

import com.ffs.algafood.core.validation.annotation.Multiple;
import com.ffs.algafood.core.validation.annotation.ZeroValueIncludeDescription;
import com.ffs.algafood.domain.model.Kitchen;
import com.ffs.algafood.domain.model.Restaurant;
import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

/**
 *
 * @author francisco
 */
@ZeroValueIncludeDescription(
        fieldValue = "shippingFee",
        fieldDescription = "name",
        requiredDescription = "Frete Grátis"
)
@Getter
@Setter
public class RestaurantRequest {

    @NotBlank
    private String name;

    @PositiveOrZero
    @Multiple(number = 5)
    private BigDecimal shippingFee;

    @Valid
    @NotNull
    private KitchenIdRequest kitchen;

    public Restaurant toModel() {
        return new ModelMapper().map(this, Restaurant.class);
    }

    public void copyPropertiesTo(Restaurant restaurant) {
        /**
         * To avoid the Hibernate a exception when trying to change the entity ID.
         * 
         * org.hibernate.HibernateException: identifier of an instance of com.domain.Entity was altered from 1 to 2
         */
        restaurant.setKitchen(new Kitchen());

        new ModelMapper().map(this, restaurant);
    }
}
