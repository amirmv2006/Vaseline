package ir.amv.os.vaseline.data.hibernate.apis.advancedsearch.server.dao;

import ir.amv.os.vaseline.data.test.model.config.TestDataModelHibernateConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(TestDataModelHibernateConfig.class)
public class TestHibernateAdvancedSearchSpringConfig {

    @Bean
    ITestCountryDao countryDao() {
        return new TestCountryDaoImpl();
    }
}
