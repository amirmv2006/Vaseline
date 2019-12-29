package ir.amv.os.vaseline.data.jpa.spring.itest.basic;

import ir.amv.os.vaseline.data.jpa.spring.basic.repo.VaselineCrudRepositoryFactory;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.VaselineITestDataSharedConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "ir.amv.os.vaseline.data.jpa.spring.itest.domain")
@EnableJpaRepositories(
        basePackages = "ir.amv.os.vaseline.data.jpa.spring.itest.domain",
        repositoryFactoryBeanClass = VaselineCrudRepositoryFactory.class
)
@Import(VaselineITestDataSharedConfig.class)
public class VaselineITestDataBasicConfig {
}
