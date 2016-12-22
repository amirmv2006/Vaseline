package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.repos;

import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.AdvancedSearchByExampleJPAProviderUtil;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity.TestCity;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.projection.IJpaCriteriaFromProvider;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.projection.ProjectionMapProvider;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.so.ITestCitySearchObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;

/**
 * Created by amv on 12/12/16.
 */
public class CityRepositoryImpl
    implements CityRepositoryCustom {

    @Autowired
    EntityManager em;

    @Override
    public List<TestCity> search(ITestCitySearchObject citySearchObject) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<TestCity> query = criteriaBuilder.createQuery(TestCity.class);
        IJpaCriteriaFromProvider map = new ProjectionMapProvider<TestCity>(query, TestCity.class);
        Predicate rootCondition = AdvancedSearchByExampleJPAProviderUtil.getRootCondition(citySearchObject, criteriaBuilder, "", map);
        if (rootCondition != null) {
            query.where(rootCondition);
        }
        List<TestCity> resultList = em.createQuery(query).getResultList();
        return resultList;
    }
}
