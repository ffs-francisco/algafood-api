package com.ffs.algafood.api.model.request.restaurant;

import com.ffs.algafood.core.validation.annotation.FileSize.FileSize;
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
    @FileSize(max = "500KB")
    private MultipartFile file;
}
