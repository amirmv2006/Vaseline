package ir.amv.os.vaseline.service.rest.itest.domain.city.impl;

import ir.amv.os.vaseline.business.spring.itest.domain.city.ITestCityCrudApi;
import ir.amv.os.vaseline.business.spring.itest.domain.city.TestCityBusinessModel;
import ir.amv.os.vaseline.service.rest.basic.api.layer.crud.impl.BaseCrudRestServiceImpl;
import ir.amv.os.vaseline.service.rest.itest.domain.city.ITestCityCrudRestService;
import ir.amv.os.vaseline.service.rest.itest.domain.city.TestCityDto;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validator;

@RestController
@Profile("basic")
@RequestMapping("cityCrud")
public class TestCityCrudRestServiceImpl
        extends BaseCrudRestServiceImpl<Long, TestCityDto, TestCityBusinessModel, ITestCityCrudApi>
        implements ITestCityCrudRestService {

    public TestCityCrudRestServiceImpl(Validator validator, ConversionService conversionService, ITestCityCrudApi api) {
        super(validator, conversionService, api);
    }

}
