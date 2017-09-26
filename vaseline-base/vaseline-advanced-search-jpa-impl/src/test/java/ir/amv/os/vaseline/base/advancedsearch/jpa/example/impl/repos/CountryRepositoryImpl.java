package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.repos;

import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.AdvancedSearchByExampleJPAProviderUtil;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity.TestCountry;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.projection.IJpaCriteriaFromProvider;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.projection.ProjectionMapProvider;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.so.ITestCountrySearchObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by amv on 12/12/16.
 */
public class CountryRepositoryImpl
    implements CountryRepositoryCustom {

    @Autowired
    EntityManager em;

    @Override
    public List<TestCountry> search(ITestCountrySearchObject countrySearchObject) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<TestCountry> query = criteriaBuilder.createQuery(TestCountry.class);
        IJpaCriteriaFromProvider map = new ProjectionMapProvider<TestCountry>(query, TestCountry.class);
        Predicate rootCondition = AdvancedSearchByExampleJPAProviderUtil.getRootCondition(countrySearchObject, criteriaBuilder, "", map);
        if (rootCondition != null) {
            query.where(rootCondition);
        }
        List<TestCountry> resultList = em.createQuery(query).getResultList();
        return resultList;
    }
}
