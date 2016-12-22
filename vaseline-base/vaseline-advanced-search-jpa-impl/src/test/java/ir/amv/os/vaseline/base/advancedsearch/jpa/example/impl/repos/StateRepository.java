package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.repos;

import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity.TestState;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by amv on 12/12/16.
 */
public interface StateRepository extends PagingAndSortingRepository<TestState, Long>, StateRepositoryCustom {
}
