package ir.amv.os.vaseline.data.test.model.server.repository.jpa;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryBusinessModel;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestCountryRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by amv on 12/12/16.
 */
public interface ITestCountryJpaRepository
        extends PagingAndSortingRepository<TestCountryBusinessModel, Long>,
        ITestCountryRepository {
}
