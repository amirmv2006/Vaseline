package ir.amv.os.vaseline.data.test.model.server.repository;

import ir.amv.os.vaseline.data.test.model.server.entity.TestContinentEntity;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by amv on 12/12/16.
 */
public interface ITestContinentRepository extends PagingAndSortingRepository<TestContinentEntity, Long> {
}
