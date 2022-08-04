package example.micronaut;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;
import java.util.Map;

@MicronautTest // <1>
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // <2>
public class BaseMysqlTest implements TestPropertyProvider { // <3>

    static GenericContainer<?> mysqlContainer;

    @Inject
    @Client("/")
    HttpClient httpClient; // <4>

    void startMySQL() {
        if (mysqlContainer == null) {
            mysqlContainer = new GenericContainer<>(DockerImageName.parse("mysql:8.0.29"))
                    .withExposedPorts(3306)
                    .withEnv("MYSQL_ROOT_PASSWORD", "my-secret-pw")
                    .withEnv("MYSQL_DATABASE", "db")
                    .waitingFor(Wait.forLogMessage(".*/usr/sbin/mysqld: ready for connections.*\\n", 2));
        }
        if (!mysqlContainer.isRunning()) {
            mysqlContainer.start();
        }
    }

    String getMySQLDbUri() {
        if (mysqlContainer == null || !mysqlContainer.isRunning()) {
            startMySQL();
        }
        return "jdbc:mysql://localhost:" + mysqlContainer.getMappedPort(3306) + "/db";
    }

    @Override
    @NonNull
    public Map<String, String> getProperties() { // <5>
        return CollectionUtils.mapOf(
                "jpa.default.properties.hibernate.connection.url", getMySQLDbUri(),
                "datasources.migration.url", getMySQLDbUri(),
                "datasources.migration.driverClassName", "com.mysql.cj.jdbc.Driver"
        );
    }

    @AfterAll
    public static void stop() {
        mysqlContainer.close();
    }
}