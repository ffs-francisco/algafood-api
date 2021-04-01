package com.ffs.algafood.api.model.request.restaurant;

import com.ffs.algafood.domain.model.restaurant.ProductPhoto;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class ProductPhotoResponse {

    private String fileName;
    private String description;
    private String contentType;
    private Long size;

    public static ProductPhotoResponse fromModel(final ProductPhoto productPhoto) {
        return new ModelMapper().map(productPhoto, ProductPhotoResponse.class);
    }
}
