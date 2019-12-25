package ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.impl;

import ir.amv.os.vaseline.data.jpa.spring.advanced.search.repo.VaselineAdvancedSearchJpaRepository;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCityAdvancedSearchRepo;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCitySearchObject;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.TestCityEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by amv on 12/12/16.
 */
@Repository
public interface ITestCityAdvancedSearchRepoVaselineImpl
        extends ITestCityAdvancedSearchRepo,
        VaselineAdvancedSearchJpaRepository<Long, TestCityEntity, ITestCitySearchObject> {
}
