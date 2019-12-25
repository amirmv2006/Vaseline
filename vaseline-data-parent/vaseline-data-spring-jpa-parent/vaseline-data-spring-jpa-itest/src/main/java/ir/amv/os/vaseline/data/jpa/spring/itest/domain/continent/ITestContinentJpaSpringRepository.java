package ir.amv.os.vaseline.data.jpa.spring.itest.domain.continent;

import ir.amv.os.vaseline.data.jpa.spring.advanced.search.repo.VaselineAdvancedSearchJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amv on 12/12/16.
 */
@Repository
public interface ITestContinentJpaSpringRepository extends
        VaselineAdvancedSearchJpaRepository<Long, TestContinentEntity, ITestContinentSearchObject> {
}
