package com.ffs.algafood.domain.service.storage;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

public interface StoragePhotoService {

    void store(final NewPhoto newPhoto);

    @Getter
    @Builder
    class NewPhoto {

        private final String fileName;
        private final InputStream inputStream;
    }
}
