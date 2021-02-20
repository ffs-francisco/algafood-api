package com.ffs.algafood.core.storage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.ffs.algafood.domain.service.storage.StoragePhotoService;
import com.ffs.algafood.infrastructor.service.storage.LocalStoragePhotoService;
import com.ffs.algafood.infrastructor.service.storage.S3StoragePhotoService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.ffs.algafood.core.storage.StorageProperties.TypeStorage.S3;

@Configuration
@AllArgsConstructor
public class StorageConfig {

    private final StorageProperties properties;

    @Bean
    public AmazonS3 amazonS3() {
        final var credentials = new BasicAWSCredentials(
                properties.getS3().getIdAccessKey(),
                properties.getS3().getSecretAccessKey()
        );

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(properties.getS3().getRegion())
                .build();
    }

    @Bean
    public StoragePhotoService storagePhotoService() {
        if (properties.getType().equals(S3)) {
            return new S3StoragePhotoService();
        } else {
            return new LocalStoragePhotoService();
        }
    }
}
