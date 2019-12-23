package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.basics.jdbc.api.dialect.IVaselineJdbcDialect;
import ir.amv.os.vaseline.data.dao.basic.api.ro.IDefaultGenericReadOnlyRepository;
import ir.amv.os.vaseline.data.dao.basic.api.ro.IBasePersistentModelRepository;
import ir.amv.os.vaseline.data.dao.basic.api.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.base.IBaseJpaRepository;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.projection.IJpaCriteriaFromProvider;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor.DefaultJpaFetchProviderImpl;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor.IJpaFetchProvider;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor.JpaFetchProviderFacade;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.vendorspecific.IVendorSpecificDaoHelper;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.List;

public interface IDefaultJpaReadOnlyRepository<I extends Serializable, E extends IBaseBusinessModel<I>>
        extends IDefaultGenericReadOnlyRepository<I, E>,
        IBasePersistentModelRepository<I, E>, IBaseJpaRepository {

    IVendorSpecificDaoHelper getVendorSpecificDaoHelper();

    default CriteriaBuilder createCriteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }

    default <QueryResult> void applyRootCondition(
            CriteriaBuilder criteriaBuilder,
            IJpaCriteriaFromProvider fromProvider,
            CriteriaQuery<QueryResult> query, // don't use E cause it might be count query
            Predicate rootCondition) {
        if (rootCondition != null) {
            query.where(rootCondition);
        }
    }

    default <X> TypedQuery<X> getTypedQuery(EntityManager entityManager, CriteriaQuery<X> criteriaQuery) {
        return getEntityManager().createQuery(criteriaQuery);
    }

    default E getEntityFromCriteria(TypedQuery<E> query) {
        List<E> resultList = query.getResultList();
        if (resultList.size() > 1) {
            throw new NonUniqueResultException("Multiple results");
        }
        return resultList.size() == 0 ? null : resultList.get(0);
    }

    default List<E> getListFromCriteria(TypedQuery<E> query) {
        return query.getResultList();
    }

    default IVaselineDataScroller<E> scrollCriteria(TypedQuery<E> query) {
        IVendorSpecificDaoHelper vendorSpecificDaoHelper = getVendorSpecificDaoHelper();
        return vendorSpecificDaoHelper.scrollQuery(getEntityManager(), query);
    }

    default <QueryResult> CriteriaQuery<QueryResult> createQuery(CriteriaBuilder criteriaBuilder, Class<QueryResult> queryResultClass) {
        return criteriaBuilder.createQuery(queryResultClass);
    }

    default IJpaFetchProvider<I, E> jpaFetchProvider() {
        return new DefaultJpaFetchProviderImpl<>();
    }

    // Read Only Dao Methods
    @Override
    default Long countAllApproximately() {
        String query = getJdbcDialect().getCountAllApproximatelyQuery();
        Query sqlQuery = getEntityManager().createNativeQuery(query);
        String tableName = getEntityTableName();
        sqlQuery.setParameter("TABLE_NAME", tableName);
        Number o = (Number) sqlQuery.getSingleResult();
        return o == null ? 0 : o.longValue();
    }

    default String getEntityTableName() {
        // TODO read table name from metadata
        return getPersistentModelClass().getAnnotation(Table.class).name();
    }

    default IVaselineJdbcDialect getJdbcDialect() {
        // TODO to be implemented
        return null;
    }

    default E getById(I id) {
        return new JpaFetchProviderFacade<I, E>(jpaFetchProvider(), this, (criteriaBuilder, query, fromProvider) -> {
            applyRootCondition(criteriaBuilder, fromProvider, query, criteriaBuilder.equal(fromProvider.getCriteriaParentProjection("id", null), id));
        }).unique();
    }

    default E getByIdDetached(I id) {
        E byId = getById(id);
        getEntityManager().detach(byId);
        return byId;
    }

    default Long countAll() {
        return allJpaCriteriaBuilderAbstractor().count();
    }

    default List<E> getAll() {
        return allJpaCriteriaBuilderAbstractor().list();
    }

    default List<E> getAll(PagingDto pagingDto) {
        return allJpaCriteriaBuilderAbstractor().page(pagingDto);
    }

    default IVaselineDataScroller<E> scrollAll(List<SortDto> sortList) {
        return allJpaCriteriaBuilderAbstractor().scroll(sortList);
    }

    default JpaFetchProviderFacade<I, E> allJpaCriteriaBuilderAbstractor() {
        return new JpaFetchProviderFacade<>(jpaFetchProvider(), this, (criteriaBuilder, query, fromProvider) -> {
            applyRootCondition(criteriaBuilder, fromProvider, query, null);
        });
    }

}
