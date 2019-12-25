package ir.amv.os.vaseline.data.jpa.spring.advanced.search.repo;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.data.advanced.search.api.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.advanced.search.api.repo.IBaseAdvancedSearchRepository;
import ir.amv.os.vaseline.data.jpa.spring.advanced.search.criteria.IBaseJpaAdvancedSearchParser;
import ir.amv.os.vaseline.data.jpa.spring.advanced.search.criteria.defimpl.DefaultJpaAdvancedSearchParserImpl;
import ir.amv.os.vaseline.data.jpa.spring.basic.projection.ProjectionMapProvider;
import ir.amv.os.vaseline.data.jpa.spring.basic.repo.IDefaultReadOnlyJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 10/3/2017.
 */
public interface IDefaultAdvancedSearchJpaRepository<I extends Serializable, E extends IBasePersistenceModel<I>, S extends  IBaseSearchObject>
        extends IBaseAdvancedSearchRepository<I, E, S>,
        IDefaultReadOnlyJpaRepository<I, E> {

    default IBaseJpaAdvancedSearchParser<S> getAdvancedSearchExampleParser(S example) {
        return new DefaultJpaAdvancedSearchParserImpl<>();
    }

    // dao methods

    @Override
    default Long countBySearchObject(S example) {
        CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
        CriteriaQuery<Number> query = createQuery(criteriaBuilder, Number.class);
        ProjectionMapProvider<E, Number> fromProvider = new ProjectionMapProvider<>(query, getPersistentModelClass());
        query.select(criteriaBuilder.count(fromProvider.getCriteriaParentProjection("", null)));
        Predicate criterion = getAdvancedSearchExampleParser(example)
                .getCriteriaFromExampleRecursively(example, IBaseSearchObject.class, criteriaBuilder, fromProvider, "");
        applyRootCondition(criteriaBuilder, fromProvider, query, criterion);
        return getCountResult(query);
    }

    @Override
    default List<E> searchBySearchObject(S example) {
        CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
        CriteriaQuery<E> query = createQuery(criteriaBuilder, getPersistentModelClass());
        ProjectionMapProvider<E, E> fromProvider = new ProjectionMapProvider<>(query, getPersistentModelClass());
        fromProvider.getCriteriaParentProjection("", null); // make sure from Entity is in query
        Predicate criterion = getAdvancedSearchExampleParser(example)
                .getCriteriaFromExampleRecursively(example, IBaseSearchObject.class, criteriaBuilder, fromProvider, "");
        applyRootCondition(criteriaBuilder, fromProvider, query, criterion);
        TypedQuery<E> typedQuery = getTypedQuery(getEntityManager(), query);
        return getListFromCriteria(typedQuery);
    }

    @Override
    default Page<E> searchBySearchObject(final S example, Pageable pagingDto) {
        CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
        CriteriaQuery<E> query = createQuery(criteriaBuilder, getPersistentModelClass());
        ProjectionMapProvider<E, E> fromProvider = new ProjectionMapProvider<>(query, getPersistentModelClass());
        fromProvider.getCriteriaParentProjection("", null); // make sure from Entity is in query
        Predicate criterion = getAdvancedSearchExampleParser(example)
                .getCriteriaFromExampleRecursively(example, IBaseSearchObject.class, criteriaBuilder, fromProvider, "");
        applyRootCondition(criteriaBuilder, fromProvider, query, criterion);
        TypedQuery<E> typedQuery = getEntityManager().createQuery(query);
        return readPage(typedQuery, getPersistentModelClass(), pagingDto, null);
    }

}
