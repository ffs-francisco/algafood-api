package com.ffs.algafood.api.model.request.restaurant;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class ProductImageRequest implements Serializable {

    @NotBlank
    private String description;

    @NotNull
    private MultipartFile file;
}
