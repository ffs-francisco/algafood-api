package com.ffs.algafood.core.storage;

import com.amazonaws.regions.Regions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Setter
@Component
@ConfigurationProperties("application.storage")
public class StorageProperties {

    private S3 s3 = new S3();
    private Local local = new Local();
    private TypeStorage type = TypeStorage.LOCAL;

    public enum TypeStorage {
        LOCAL, S3
    }

    @Getter
    @Setter
    public class Local {

        private Path photoDirectory;
    }

    @Getter
    @Setter
    public class S3 {

        private String idAccessKey;
        private String secretAccessKey;
        private String bucket;
        private Regions region;
        private String photoDirectory;
    }
}
