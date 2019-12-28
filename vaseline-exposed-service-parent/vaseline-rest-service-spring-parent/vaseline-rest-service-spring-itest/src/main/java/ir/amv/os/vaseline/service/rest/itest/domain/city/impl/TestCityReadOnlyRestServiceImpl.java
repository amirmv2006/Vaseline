package ir.amv.os.vaseline.service.rest.itest.domain.city.impl;

import ir.amv.os.vaseline.business.spring.itest.domain.city.ITestCityReadOnlyApi;
import ir.amv.os.vaseline.business.spring.itest.domain.city.TestCityBusinessModel;
import ir.amv.os.vaseline.service.rest.basic.api.layer.ro.impl.BaseReadOnlyRestServiceImpl;
import ir.amv.os.vaseline.service.rest.itest.domain.city.ITestCityReadOnlyRestService;
import ir.amv.os.vaseline.service.rest.itest.domain.city.TestCityDto;
import org.springframework.core.convert.ConversionService;

import javax.validation.Validator;

public class TestCityReadOnlyRestServiceImpl
        extends BaseReadOnlyRestServiceImpl<Long, TestCityDto, TestCityBusinessModel, ITestCityReadOnlyApi>
        implements ITestCityReadOnlyRestService {

    public TestCityReadOnlyRestServiceImpl(Validator validator, ConversionService conversionService, ITestCityReadOnlyApi api) {
        super(validator, conversionService, api);
    }

}
