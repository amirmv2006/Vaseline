package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.projection.ProjectionMapProvider;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaReadOnlyDao;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public interface IJpaFetchProvider<E extends IBaseEntity<Id>, Id extends Serializable> {

    default Long count(IDefaultJpaReadOnlyDao<E, Id> dao, IJpaCriteriaPrunerFunctionalInterface<Number> criteriaPruner) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<Number> query = dao.createQuery(criteriaBuilder, Number.class);
        ProjectionMapProvider<E, Number> fromProvider = new ProjectionMapProvider<>(query, (Class<E>)dao.getEntityClass());
        query.select(criteriaBuilder.count(fromProvider.getCriteriaParentProjection("", null)));
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        return dao.getCountResult(query);
    }

    default List<E> list(IDefaultJpaReadOnlyDao<E, Id> dao, IJpaCriteriaPrunerFunctionalInterface<E> criteriaPruner) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<E> query = dao.createQuery(criteriaBuilder, (Class<E>)dao.getEntityClass());
        ProjectionMapProvider<E, E> fromProvider = new ProjectionMapProvider<>(query, (Class<E>)dao.getEntityClass());
        fromProvider.getCriteriaParentProjection("", null); // make sure from Entity is in query
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        TypedQuery<E> typedQuery = dao.getTypedQuery(dao.getEntityManager(), query);
        return dao.getListFromCriteria(typedQuery);
    }

    default List<E> page(IDefaultJpaReadOnlyDao<E, Id> dao, IJpaCriteriaPrunerFunctionalInterface<E> criteriaPruner, PagingDto pagingDto) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<E> query = dao.createQuery(criteriaBuilder, (Class<E>)dao.getEntityClass());
        ProjectionMapProvider<E, E> fromProvider = new ProjectionMapProvider<>(query, (Class<E>)dao.getEntityClass());
        fromProvider.getCriteriaParentProjection("", null); // make sure from Entity is in query
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        TypedQuery<E> typedQuery = dao.paginateCriteria(dao.getEntityManager(), criteriaBuilder, fromProvider, query, pagingDto);
        return dao.getListFromCriteria(typedQuery);
    }

    default IVaselineDataScroller<E> scroll(IDefaultJpaReadOnlyDao<E, Id> dao, IJpaCriteriaPrunerFunctionalInterface<E> criteriaPruner, List<SortDto> sortList) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<E> query = dao.createQuery(criteriaBuilder, (Class<E>)dao.getEntityClass());
        ProjectionMapProvider<E, E> fromProvider = new ProjectionMapProvider<>(query, (Class<E>)dao.getEntityClass());
        fromProvider.getCriteriaParentProjection("", null); // make sure from Entity is in query
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        TypedQuery<E> typedQuery = dao.paginateCriteria(dao.getEntityManager(), criteriaBuilder, fromProvider, query, new PagingDto(sortList, null, null));
        return dao.scrollCriteria(typedQuery);
    }

    default E unique(IDefaultJpaReadOnlyDao<E, Id> dao, IJpaCriteriaPrunerFunctionalInterface<E> criteriaPruner) {
        CriteriaBuilder criteriaBuilder = dao.createCriteriaBuilder();
        CriteriaQuery<E> query = dao.createQuery(criteriaBuilder, (Class<E>)dao.getEntityClass());
        ProjectionMapProvider<E, E> fromProvider = new ProjectionMapProvider<>(query, (Class<E>)dao.getEntityClass());
        fromProvider.getCriteriaParentProjection("", null); // make sure from Entity is in query
        criteriaPruner.pruneCriteria(criteriaBuilder, query, fromProvider);
        TypedQuery<E> typedQuery = dao.getTypedQuery(dao.getEntityManager(), query);
        return dao.getEntityFromCriteria(typedQuery);
    }
}
