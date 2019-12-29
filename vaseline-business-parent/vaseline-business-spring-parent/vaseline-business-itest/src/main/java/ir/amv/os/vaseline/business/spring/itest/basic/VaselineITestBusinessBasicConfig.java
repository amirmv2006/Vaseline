package ir.amv.os.vaseline.business.spring.itest.basic;

import ir.amv.os.vaseline.business.spring.itest.domain.city.impl.TestCityCrudApiImpl;
import ir.amv.os.vaseline.business.spring.itest.domain.city.impl.TestCityReadOnlyApiImpl;
import ir.amv.os.vaseline.data.jpa.spring.itest.basic.VaselineITestDataBasicConfig;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCityCrudRepo;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCityReadOnlyRepo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;

@SpringBootApplication
@Import(VaselineITestDataBasicConfig.class)
public class VaselineITestBusinessBasicConfig {

    @Bean
    public TestCityReadOnlyApiImpl cityReadOnlyApi(
            final ConversionService conversionSvc,
            final ITestCityReadOnlyRepo cityReadOnlyRepo) {
        return new TestCityReadOnlyApiImpl(conversionSvc, cityReadOnlyRepo);
    }

    @Bean
    public TestCityCrudApiImpl cityCrudApi(
            final ConversionService conversionSvc,
            final ITestCityCrudRepo cityCrudRepo) {
        return new TestCityCrudApiImpl(conversionSvc, cityCrudRepo);
    }
}
