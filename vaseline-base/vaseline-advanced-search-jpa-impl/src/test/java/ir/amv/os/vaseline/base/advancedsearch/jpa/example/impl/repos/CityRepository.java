package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.repos;

import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity.TestCity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by amv on 12/12/16.
 */
public interface CityRepository extends PagingAndSortingRepository<TestCity, Long>, CityRepositoryCustom {
}
