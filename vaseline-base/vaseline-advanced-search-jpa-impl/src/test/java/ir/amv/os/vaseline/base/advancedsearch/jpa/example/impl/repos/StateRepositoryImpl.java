package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.repos;

import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.AdvancedSearchByExampleJPAProviderUtil;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity.TestState;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.projection.IJpaCriteriaFromProvider;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.projection.ProjectionMapProvider;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.so.ITestStateSearchObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by amv on 12/12/16.
 */
public class StateRepositoryImpl
    implements StateRepositoryCustom {

    @Autowired
    EntityManager em;

    @Override
    public List<TestState> search(ITestStateSearchObject stateSearchObject) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<TestState> query = criteriaBuilder.createQuery(TestState.class);
        IJpaCriteriaFromProvider map = new ProjectionMapProvider<TestState>(query, TestState.class);
        Predicate rootCondition = AdvancedSearchByExampleJPAProviderUtil.getRootCondition(stateSearchObject, criteriaBuilder, "", map);
        if (rootCondition != null) {
            query.where(rootCondition);
        }
        List<TestState> resultList = em.createQuery(query).getResultList();
        return resultList;
    }
}
