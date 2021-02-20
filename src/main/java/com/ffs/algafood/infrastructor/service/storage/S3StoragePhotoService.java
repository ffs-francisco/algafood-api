package com.ffs.algafood.infrastructor.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ffs.algafood.core.storage.StorageProperties;
import com.ffs.algafood.domain.service.storage.StoragePhotoService;
import com.ffs.algafood.infrastructor.service.storage.exception.StorageException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.amazonaws.services.s3.model.CannedAccessControlList.PublicRead;

@Service
@AllArgsConstructor
public class S3StoragePhotoService implements StoragePhotoService {

    private final AmazonS3 amazonS3;
    private final StorageProperties properties;

    @Override
    public void store(final NewPhoto newPhoto) {
        try {
            final var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(newPhoto.getContentType());

            final var putObjectRequest = new PutObjectRequest(
                    properties.getS3().getBucket(),
                    this.getFilePath(newPhoto.getFileName()),
                    newPhoto.getInputStream(),
                    objectMetadata
            ).withCannedAcl(PublicRead);

            this.amazonS3.putObject(putObjectRequest);
        } catch (Exception ex) {
            throw new StorageException("Could not store the file in Amazon S3", ex);
        }
    }

    @Override
    public void remove(final String fileName) {
        try {
            final var deleteObjectRequest = new DeleteObjectRequest(
                    properties.getS3().getBucket(),
                    this.getFilePath(fileName)
            );

            this.amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception ex) {
            throw new StorageException("Could not remove the file in Amazon S3", ex);
        }

    }

    @Override
    public RecoverPhoto recover(final String fileName) {
        final var filePath = this.getFilePath(fileName);
        final var url = this.amazonS3.getUrl(properties.getS3().getBucket(), filePath);

        return RecoverPhoto.builder()
                .url(url.toString())
                .build();
    }

    private String getFilePath(final String fileName) {
        return String.format("%s/%s", properties.getS3().getPhotoDirectory(), fileName);
    }
}
