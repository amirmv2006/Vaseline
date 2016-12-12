package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl;

import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.projection.ProjectionMapProvider;
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
    public List<City> search(ICitySearchObject citySearchObject) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<City> query = criteriaBuilder.createQuery(City.class);
        Map<String, Path<?>> map = new ProjectionMapProvider<City>(query, City.class);
        Predicate rootCondition = AdvancedSearchByExampleJPAProviderUtil.getRootCondition(citySearchObject, criteriaBuilder, "", map);
        if (rootCondition != null) {
            query.where(rootCondition);
        }
        List<City> resultList = em.createQuery(query).getResultList();
        return resultList;
    }
}
