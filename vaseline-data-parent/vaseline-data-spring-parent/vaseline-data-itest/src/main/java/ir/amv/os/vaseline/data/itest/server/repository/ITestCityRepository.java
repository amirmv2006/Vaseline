package ir.amv.os.vaseline.data.itest.server.repository;

import ir.amv.os.vaseline.data.itest.server.entity.TestCityEntity;
import ir.amv.os.vaseline.data.itest.shared.searchobject.ITestCitySearchObject;
import ir.amv.os.vaseline.data.jpa.search.advanced.spring.IBaseAdvancedSearchSpringRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amv on 12/12/16.
 */
@Repository
public interface ITestCityRepository extends
        IBaseAdvancedSearchSpringRepository<Long, TestCityEntity, ITestCitySearchObject> {
}
