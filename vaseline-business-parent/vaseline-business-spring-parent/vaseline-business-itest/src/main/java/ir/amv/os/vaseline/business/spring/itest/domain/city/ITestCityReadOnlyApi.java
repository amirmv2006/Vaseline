package ir.amv.os.vaseline.business.spring.itest.domain.city;

import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;

import java.util.List;

public interface ITestCityReadOnlyApi extends IBaseReadOnlyApi<Long, TestCityBusinessEntity> {

    List<TestCityBusinessEntity> getCitiesOfState(String stateName) throws BaseBusinessException;
}
