package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.projection.ProjectionMapProvider;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.IBaseJpaReadOnlyDao;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public interface IJpaFetchProvider<E extends IBaseEntity<Id>, Id extends Serializable> {

    default Long count(IBaseJpaReadOnlyDao<E, Id> dao, IJpaCriteriaPrunerFunctionalInterface<Number> criteriaPruner) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<Number> query = dao.createQuery(criteriaBuilder, Number.class);
        ProjectionMapProvider<Number> fromProvider = new ProjectionMapProvider<>(query, Number.class);
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        return dao.getCountResult(query);
    }

    default List<E> list(IBaseJpaReadOnlyDao<E, Id> dao, IJpaCriteriaPrunerFunctionalInterface<E> criteriaPruner) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<E> query = dao.createQuery(criteriaBuilder, dao.getEntityClass());
        ProjectionMapProvider<E> fromProvider = new ProjectionMapProvider<>(query, dao.getEntityClass());
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        TypedQuery<E> typedQuery = dao.getTypedQuery(query);
        return dao.getListFromCriteria(typedQuery);
    }

    default List<E> page(IBaseJpaReadOnlyDao<E, Id> dao, IJpaCriteriaPrunerFunctionalInterface<E> criteriaPruner, PagingDto pagingDto) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<E> query = dao.createQuery(criteriaBuilder, dao.getEntityClass());
        ProjectionMapProvider<E> fromProvider = new ProjectionMapProvider<>(query, dao.getEntityClass());
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        TypedQuery<E> typedQuery = dao.paginateCriteria(dao.getEntityManager(), criteriaBuilder, fromProvider, query, pagingDto);
        return dao.getListFromCriteria(typedQuery);
    }

    default IVaselineDataScroller<E> scroll(IBaseJpaReadOnlyDao<E, Id> dao, IJpaCriteriaPrunerFunctionalInterface<E> criteriaPruner) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<E> query = dao.createQuery(criteriaBuilder, dao.getEntityClass());
        ProjectionMapProvider<E> fromProvider = new ProjectionMapProvider<>(query, dao.getEntityClass());
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        TypedQuery<E> typedQuery = dao.getTypedQuery(query);
        return dao.scrollCriteria(typedQuery);
    }

    default E unique(IBaseJpaReadOnlyDao<E, Id> dao, IJpaCriteriaPrunerFunctionalInterface<E> criteriaPruner) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<E> query = dao.createQuery(criteriaBuilder, dao.getEntityClass());
        ProjectionMapProvider<E> fromProvider = new ProjectionMapProvider<>(query, dao.getEntityClass());
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        TypedQuery<E> typedQuery = dao.getTypedQuery(query);
        return dao.getEntityFromCriteria(typedQuery);
    }
}
