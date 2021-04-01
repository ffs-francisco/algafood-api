package com.ffs.algafood.api.model.request.restaurant;

import com.ffs.algafood.core.validation.annotation.ZeroValueIncludeDescription.ZeroValueIncludeDescription;
import com.ffs.algafood.core.validation.annotation.mutilple.Multiple;
import com.ffs.algafood.domain.model.City;
import com.ffs.algafood.domain.model.Kitchen;
import com.ffs.algafood.domain.model.restaurant.Restaurant;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

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
    private AddressRequest address;

    @Valid
    @NotNull
    private KitchenIdRequest kitchen;

    public Restaurant toModel() {
        return new ModelMapper().map(this, Restaurant.class);
    }

    public void copyPropertiesTo(Restaurant restaurant) {
        /**
         * To avoid the a exception when trying to change the entity ID.
         *
         * org.hibernate.HibernateException: identifier of an instance of com.domain.Entity was altered from 1 to 2
         */
        restaurant.setKitchen(new Kitchen());
        if (restaurant.getAddress() != null) {
            restaurant.getAddress().setCity(new City());
        }

        new ModelMapper().map(this, restaurant);
    }
}
