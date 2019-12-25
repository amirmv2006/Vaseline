package ir.amv.os.vaseline.data.jpa.spring.itest.domain.city;

import ir.amv.os.vaseline.data.basic.api.base.IBaseReadOnlyRepository;

import java.util.Optional;

/**
 * Created by amv on 12/12/16.
 */
public interface ITestCityReadOnlyRepo extends
        IBaseReadOnlyRepository<Long, TestCityEntity> {

    Optional<TestCityEntity> findByCityName(String cityName);

}
