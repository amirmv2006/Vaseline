package ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.impl;

import ir.amv.os.vaseline.data.jpa.spring.basic.repo.VaselineCrudJpaRepository;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCityCrudRepo;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.TestCityEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by amv on 12/12/16.
 */
@Repository
public interface ITestCityCrudRepoVaslienImpl
        extends ITestCityCrudRepo,
        VaselineCrudJpaRepository<Long, TestCityEntity> {
}
