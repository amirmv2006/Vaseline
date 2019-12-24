package ir.amv.os.vaseline.data.test.model.server.repository.jpa;

import ir.amv.os.vaseline.data.test.model.server.entity.TestContinentBusinessModel;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestContinentRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by amv on 12/12/16.
 */
public interface ITestContinentJpaRepository
        extends PagingAndSortingRepository<TestContinentBusinessModel, Long>,
        ITestContinentRepository {
}
