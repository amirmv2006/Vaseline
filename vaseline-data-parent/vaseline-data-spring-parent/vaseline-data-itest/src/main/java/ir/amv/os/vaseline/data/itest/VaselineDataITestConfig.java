package ir.amv.os.vaseline.data.itest;

import ir.amv.os.vaseline.data.jpa.search.advanced.spring.AdvancedSearchRepositoryFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = AdvancedSearchRepositoryFactory.class)
public class VaselineDataITestConfig {
}
