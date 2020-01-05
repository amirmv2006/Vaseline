package ir.amv.os.vaseline.business.spring.itest.domain.city;

import ir.amv.os.vaseline.business.search.advanced.api.server.IBaseAdvancedSearchApi;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCitySearchObject;

public interface ITestCityAdvancedSearchApi
        extends IBaseAdvancedSearchApi<Long, TestCityBusinessEntity, ITestCitySearchObject> {
}
