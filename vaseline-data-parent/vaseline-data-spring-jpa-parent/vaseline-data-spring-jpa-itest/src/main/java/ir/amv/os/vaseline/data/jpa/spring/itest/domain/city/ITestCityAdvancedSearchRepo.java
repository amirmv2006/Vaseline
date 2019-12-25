package ir.amv.os.vaseline.data.jpa.spring.itest.domain.city;

import ir.amv.os.vaseline.data.advanced.search.api.repo.IBaseAdvancedSearchRepository;

/**
 * Created by amv on 12/12/16.
 */
public interface ITestCityAdvancedSearchRepo extends
        IBaseAdvancedSearchRepository<Long, TestCityEntity, ITestCitySearchObject> {
}
