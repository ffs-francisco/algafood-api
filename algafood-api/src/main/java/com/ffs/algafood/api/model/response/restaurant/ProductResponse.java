package com.ffs.algafood.api.model.response.restaurant;

import com.ffs.algafood.domain.model.restaurant.Product;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author francisco
 */
@Setter
@Getter
public class ProductResponse implements Serializable {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;

    public static ProductResponse from(final Product product) {
        return new ModelMapper().map(product, ProductResponse.class);
    }

    public static List<ProductResponse> fromList(final List<Product> products) {
        return products.stream()
                .map(ProductResponse::from)
                .collect(Collectors.toList());
    }

}
