package com.ffs.algafood.api.model.request.restaurant;

import com.ffs.algafood.core.validation.annotation.FileContentType.FileContentType;
import com.ffs.algafood.core.validation.annotation.FileSize.FileSize;
import com.ffs.algafood.domain.service.storage.StoragePhotoService.NewPhoto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.Serializable;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

/**
 * @author francisco
 */
@Getter
@Setter
public class ProductPhotoRequest implements Serializable {

    @NotBlank
    private String description;

    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = {IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE})
    private MultipartFile file;

    public NewPhoto getPhoto() throws IOException {
        return NewPhoto.builder()
                .description(this.description)
                .size(this.file.getSize())
                .originalFileName(this.file.getOriginalFilename())
                .contentType(this.file.getContentType())
                .inputStream(this.file.getInputStream())
                .build();
    }
}
