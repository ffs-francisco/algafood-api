package com.ffs.algafood.infrastructor.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.ffs.algafood.domain.service.storage.StoragePhotoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@AllArgsConstructor
public class S3StoragePhotoService implements StoragePhotoService {

    private final AmazonS3 amazonS3;

    @Override
    public void store(NewPhoto newPhoto) {

    }

    @Override
    public void remove(String fileName) {

    }

    @Override
    public InputStream recover(String fileName) {
        return null;
    }
}
