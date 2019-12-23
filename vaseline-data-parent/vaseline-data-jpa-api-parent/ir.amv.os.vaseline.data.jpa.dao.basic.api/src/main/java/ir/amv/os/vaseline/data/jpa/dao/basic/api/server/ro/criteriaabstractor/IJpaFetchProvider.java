package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.projection.ProjectionMapProvider;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaReadOnlyRepository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public interface IJpaFetchProvider<I extends Serializable, E extends IBaseBusinessModel<I>> {

    default Long count(IDefaultJpaReadOnlyRepository<I, E> dao, IJpaCriteriaPrunerFunctionalInterface<Number> criteriaPruner) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<Number> query = dao.createQuery(criteriaBuilder, Number.class);
        ProjectionMapProvider<E, Number> fromProvider = new ProjectionMapProvider<>(query, (Class<E>)dao.getPersistentModelClass());
        query.select(criteriaBuilder.count(fromProvider.getCriteriaParentProjection("", null)));
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        return dao.getCountResult(query);
    }

    default List<E> list(IDefaultJpaReadOnlyRepository<I, E> dao, IJpaCriteriaPrunerFunctionalInterface<E> criteriaPruner) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<E> query = dao.createQuery(criteriaBuilder, (Class<E>)dao.getPersistentModelClass());
        ProjectionMapProvider<E, E> fromProvider = new ProjectionMapProvider<>(query, (Class<E>)dao.getPersistentModelClass());
        fromProvider.getCriteriaParentProjection("", null); // make sure from Entity is in query
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        TypedQuery<E> typedQuery = dao.getTypedQuery(dao.getEntityManager(), query);
        return dao.getListFromCriteria(typedQuery);
    }

    default List<E> page(IDefaultJpaReadOnlyRepository<I, E> dao, IJpaCriteriaPrunerFunctionalInterface<E> criteriaPruner, PagingDto pagingDto) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<E> query = dao.createQuery(criteriaBuilder, (Class<E>)dao.getPersistentModelClass());
        ProjectionMapProvider<E, E> fromProvider = new ProjectionMapProvider<>(query, (Class<E>)dao.getPersistentModelClass());
        fromProvider.getCriteriaParentProjection("", null); // make sure from Entity is in query
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        TypedQuery<E> typedQuery = dao.paginateCriteria(dao.getEntityManager(), criteriaBuilder, fromProvider, query, pagingDto);
        return dao.getListFromCriteria(typedQuery);
    }

    default IVaselineDataScroller<E> scroll(IDefaultJpaReadOnlyRepository<I, E> dao, IJpaCriteriaPrunerFunctionalInterface<E> criteriaPruner, List<SortDto> sortList) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<E> query = dao.createQuery(criteriaBuilder, (Class<E>)dao.getPersistentModelClass());
        ProjectionMapProvider<E, E> fromProvider = new ProjectionMapProvider<>(query, (Class<E>)dao.getPersistentModelClass());
        fromProvider.getCriteriaParentProjection("", null); // make sure from Entity is in query
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        TypedQuery<E> typedQuery = dao.paginateCriteria(dao.getEntityManager(), criteriaBuilder, fromProvider, query, new PagingDto(sortList, null, null));
        return dao.scrollCriteria(typedQuery);
    }

    default E unique(IDefaultJpaReadOnlyRepository<I, E> dao, IJpaCriteriaPrunerFunctionalInterface<E> criteriaPruner) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<E> query = dao.createQuery(criteriaBuilder, (Class<E>)dao.getPersistentModelClass());
        ProjectionMapProvider<E, E> fromProvider = new ProjectionMapProvider<>(query, (Class<E>)dao.getPersistentModelClass());
        fromProvider.getCriteriaParentProjection("", null); // make sure from Entity is in query
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        TypedQuery<E> typedQuery = dao.getTypedQuery(dao.getEntityManager(), query);
        return dao.getEntityFromCriteria(typedQuery);
    }
}
