package ir.amv.os.vaseline.data.jpa.spring.itest.advanced.search;

import ir.amv.os.vaseline.data.jpa.spring.advanced.search.repo.VaselineAdvancedSearchRepositoryFactory;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.SharedConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "ir.amv.os.vaseline.data.jpa.spring.itest.domain")
@EnableJpaRepositories(
        basePackages = "ir.amv.os.vaseline.data.jpa.spring.itest.domain",
        repositoryFactoryBeanClass = VaselineAdvancedSearchRepositoryFactory.class
)
@Import(SharedConfig.class)
public class VaselineDataAdvancedSearchITestConfig {
}
