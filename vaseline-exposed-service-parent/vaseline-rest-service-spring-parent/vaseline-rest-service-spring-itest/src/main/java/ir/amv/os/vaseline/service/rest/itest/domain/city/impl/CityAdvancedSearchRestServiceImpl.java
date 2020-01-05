package ir.amv.os.vaseline.service.rest.itest.domain.city.impl;

import ir.amv.os.vaseline.business.spring.itest.domain.city.ITestCityAdvancedSearchApi;
import ir.amv.os.vaseline.business.spring.itest.domain.city.TestCityBusinessEntity;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCitySearchObject;
import ir.amv.os.vaseline.service.rest.advanced.search.layer.impl.BaseAdvancedSearchRestServiceImpl;
import ir.amv.os.vaseline.service.rest.itest.domain.city.ICityAdvancedSearchRestService;
import ir.amv.os.vaseline.service.rest.itest.domain.city.TestCityDto;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validator;

@RestController
@Profile("AdvancedSearch")
@RequestMapping("cityAS")
public class CityAdvancedSearchRestServiceImpl
        extends BaseAdvancedSearchRestServiceImpl<Long, TestCityDto, TestCityBusinessEntity, ITestCitySearchObject, ITestCityAdvancedSearchApi>
        implements ICityAdvancedSearchRestService {

    public CityAdvancedSearchRestServiceImpl(Validator validator, ConversionService conversionService, ITestCityAdvancedSearchApi api) {
        super(validator, conversionService, api);
    }
}
