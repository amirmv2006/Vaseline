package ir.amv.os.vaseline.data.jpa.apis.advancedsearch.server.dao;

import ir.amv.os.vaseline.data.test.model.config.TestDataModelJpaConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(TestDataModelJpaConfig.class)
public class TestJpaAdvancedSearchSpringConfig {

    @Bean
    ITestCountryDao countryDao() {
        return new TestCountryDaoImpl();
    }
}
