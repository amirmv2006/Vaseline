package ir.amv.os.vaseline.service.rest.itest.domain.city;

import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCitySearchObject;
import ir.amv.os.vaseline.service.rest.advanced.search.layer.IBaseAdvancedSearchRestService;

public interface ICityAdvancedSearchRestService
        extends IBaseAdvancedSearchRestService<Long, TestCityDto, ITestCitySearchObject> {
}
