package com.ffs.algafood.domain.service.storage;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface StoragePhotoService {

    void store(final NewPhoto newPhoto);

    void remove(final String fileName);

    default void update(final String oldFileName, final NewPhoto newPhoto) {
        this.store(newPhoto);

        if (oldFileName != null){
            this.remove(oldFileName);
        }
    }

    @Getter
    class NewPhoto {

        private final Long size;
        private final String fileName;
        private final String originalFileName;
        private final String description;
        private final String contentType;
        private final InputStream inputStream;

        @Builder
        public NewPhoto(Long size, String originalFileName, String description, String contentType, InputStream inputStream) {
            this.size = size;
            this.fileName = UUID.randomUUID().toString() + "_" + originalFileName;
            this.originalFileName = originalFileName;
            this.description = description;
            this.contentType = contentType;
            this.inputStream = inputStream;
        }
    }
}
