package ir.amv.os.vaseline.service.rest.itest.domain.city.impl;

import ir.amv.os.vaseline.business.spring.itest.domain.city.ITestCityReadOnlyApi;
import ir.amv.os.vaseline.business.spring.itest.domain.city.TestCityBusinessEntity;
import ir.amv.os.vaseline.service.rest.basic.api.layer.ro.impl.BaseReadOnlyRestServiceImpl;
import ir.amv.os.vaseline.service.rest.itest.domain.city.ITestCityReadOnlyRestService;
import ir.amv.os.vaseline.service.rest.itest.domain.city.TestCityDto;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validator;

@RestController
@Profile("basic")
@RequestMapping("cityRO")
public class TestCityReadOnlyRestServiceImpl
        extends BaseReadOnlyRestServiceImpl<Long, TestCityDto, TestCityBusinessEntity, ITestCityReadOnlyApi>
        implements ITestCityReadOnlyRestService {

    public TestCityReadOnlyRestServiceImpl(Validator validator, ConversionService conversionService, ITestCityReadOnlyApi api) {
        super(validator, conversionService, api);
    }

}
