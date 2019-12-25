package ir.amv.os.vaseline.data.jpa.spring.itest.domain.state;

import ir.amv.os.vaseline.data.jpa.spring.advanced.search.repo.VaselineAdvancedSearchJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amv on 12/12/16.
 */
@Repository
public interface ITestStateJpaSpringRepository extends
        VaselineAdvancedSearchJpaRepository<Long, TestStateEntity, ITestStateSearchObject> {
}
