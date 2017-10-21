package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro;

import ir.amv.os.vaseline.data.test.model.config.TestDataModelJpaConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(TestDataModelJpaConfig.class)
public class TestJpaDaoSpringConfig {

    @Bean
    ITestCountryDao countryDao() {
        return new TestCountryDaoImpl();
    }

}
