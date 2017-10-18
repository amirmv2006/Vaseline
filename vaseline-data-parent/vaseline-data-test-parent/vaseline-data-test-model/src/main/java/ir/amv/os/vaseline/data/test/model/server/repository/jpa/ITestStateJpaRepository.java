package ir.amv.os.vaseline.data.test.model.server.repository.jpa;

import ir.amv.os.vaseline.data.test.model.server.entity.TestStateEntity;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestStateRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by amv on 12/12/16.
 */
public interface ITestStateJpaRepository
        extends PagingAndSortingRepository<TestStateEntity, Long>,
        ITestStateRepository {
}