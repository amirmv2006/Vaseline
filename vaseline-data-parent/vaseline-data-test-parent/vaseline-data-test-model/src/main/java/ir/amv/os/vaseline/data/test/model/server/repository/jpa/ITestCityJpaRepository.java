package ir.amv.os.vaseline.data.test.model.server.repository.jpa;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCityBusinessModel;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestCityRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by amv on 12/12/16.
 */
public interface ITestCityJpaRepository
        extends PagingAndSortingRepository<TestCityBusinessModel, Long>,
        ITestCityRepository {
}
