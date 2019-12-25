package ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.impl;

import ir.amv.os.vaseline.data.jpa.spring.basic.repo.VaselineReadOnlyJpaRepository;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCityReadOnlyRepo;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.TestCityEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITestCityReadOnlyRepoVaselineImpl
        extends ITestCityReadOnlyRepo,
        VaselineReadOnlyJpaRepository<Long, TestCityEntity> {


}
