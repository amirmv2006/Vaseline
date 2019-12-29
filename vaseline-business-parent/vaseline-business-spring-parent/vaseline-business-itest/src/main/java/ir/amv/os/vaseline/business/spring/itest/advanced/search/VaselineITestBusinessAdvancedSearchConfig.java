package ir.amv.os.vaseline.business.spring.itest.advanced.search;

import ir.amv.os.vaseline.business.spring.itest.domain.city.impl.TestCityAdvancedSearchApiImpl;
import ir.amv.os.vaseline.data.jpa.spring.itest.advanced.search.VaselineITestDataAdvancedSearchConfig;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCityAdvancedSearchRepo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;

@SpringBootApplication
@Import(VaselineITestDataAdvancedSearchConfig.class)
public class VaselineITestBusinessAdvancedSearchConfig {

    @Bean
    public TestCityAdvancedSearchApiImpl cityReadOnlyApi(
            final ConversionService conversionSvc,
            final ITestCityAdvancedSearchRepo cityRepo) {
        return new TestCityAdvancedSearchApiImpl(conversionSvc, cityRepo);
    }

}
