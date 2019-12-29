package ir.amv.os.vaseline.data.jpa.spring.itest.domain.city;

import ir.amv.os.vaseline.data.basic.api.base.IBaseReadOnlyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Created by amv on 12/12/16.
 */
public interface ITestCityReadOnlyRepo extends
        IBaseReadOnlyRepository<Long, TestCityEntity> {

    CompletableFuture<Optional<TestCityEntity>> findByCityName(String cityName);

    @Query("Select c from TestCityEntity c join fetch c.state s where s.stateName=:stateName")
    CompletableFuture<List<TestCityEntity>> findByStateName(
            @Param("stateName") String stateName
    );

}
