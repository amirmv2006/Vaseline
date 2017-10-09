package ir.amv.os.vaseline.data.test.model.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "ir.amv.os.vaseline.data.test.model.server")
@EntityScan(basePackages = "ir.amv.os.vaseline.data.test.model.server.entity")
public class TestDataModelConfig {
}
