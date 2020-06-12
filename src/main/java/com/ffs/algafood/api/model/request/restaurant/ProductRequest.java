package com.ffs.algafood.api.model.request.restaurant;

import com.ffs.algafood.domain.model.restaurant.Product;
import com.ffs.algafood.domain.model.restaurant.Restaurant;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Getter
@Setter
public class ProductRequest implements Serializable {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @NotNull
    private Boolean active;

    public Product toModel(final Long restaurantId) {
        final var restaurant = new Restaurant();
        restaurant.setId(restaurantId);

        final var product = new ModelMapper().map(this, Product.class);
        product.setRestaurant(restaurant);

        return product;
    }
}
