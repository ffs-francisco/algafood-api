package com.ffs.algafood.infrastructor.service.storage;

import com.ffs.algafood.core.storage.StorageProperties;
import com.ffs.algafood.domain.service.storage.StoragePhotoService;
import com.ffs.algafood.infrastructor.service.storage.exception.StorageException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@AllArgsConstructor
public class LocalStoragePhotoService implements StoragePhotoService {

    private final StorageProperties storageProperties;

    @Override
    public void store(final NewPhoto newPhoto) {
        final var filePath = this.getFilePath(newPhoto.getFileName());

        try {
            FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(filePath));
        } catch (IOException e) {
            throw new StorageException("Could not store the file.", e);
        }
    }


    @Override
    public void remove(final String fileName) {
        try {
            Files.deleteIfExists(this.getFilePath(fileName));
        } catch (IOException e) {
            throw new StorageException("Could not remove the file.", e);
        }
    }


    @Override
    public InputStream recover(final String fileName) {
        final var path = getFilePath(fileName);

        try {
            return Files.newInputStream(path);
        } catch (IOException e) {
            throw new StorageException("Could not recover the file", e);
        }
    }

    private Path getFilePath(final String fileName) {
        return this.storageProperties.getLocal().getPhotoDirectory().resolve(Path.of(fileName));
    }
}
