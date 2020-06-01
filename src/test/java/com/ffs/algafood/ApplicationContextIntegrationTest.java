package com.ffs.algafood;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;
import com.wix.mysql.distribution.Version;
import java.util.TimeZone;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.SocketUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.wix.mysql.config.Charset.UTF8;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class ApplicationContextIntegrationTest extends ApplicationWebContextIntegrationTest {

    private static EmbeddedMysql embeddedMysql;

    @BeforeAll
    public static void setup() {
        var randomMysqlPort = SocketUtils.findAvailableTcpPort();
        var randomMysqlSchema = UUID.randomUUID().toString();

        System.setProperty("api.test.database-port", String.valueOf(randomMysqlPort));
        System.setProperty("api.test.database-schema", randomMysqlSchema);

        startEmbeddedMysql(randomMysqlPort, randomMysqlSchema);
    }

    @AfterAll
    public static void teardown() {
        embeddedMysql.stop();
    }

    private static void startEmbeddedMysql(final Integer port, final String schema) {
        embeddedMysql = EmbeddedMysql
                .anEmbeddedMysql(MysqldConfig
                        .aMysqldConfig(Version.v8_latest)
                        .withTimeZone(TimeZone.getTimeZone("UTC"))
                        .withTimeout(2, TimeUnit.MINUTES)
                        .withCharset(UTF8)
                        .withPort(port)
                        .withUser("algafood", "algafood")
                        .build())
                .addSchema(SchemaConfig
                        .aSchemaConfig(schema)
                        .build())
                .start();
    }
}
