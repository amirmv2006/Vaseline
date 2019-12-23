package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.data.dao.basic.api.ro.IBasePersistentModelRepository;
import ir.amv.os.vaseline.data.dao.basic.api.ro.IDefaultGenericReadOnlyRepository;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.base.IBaseJpaRepository;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.projection.IJpaCriteriaFromProvider;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.List;

public interface IDefaultJpaPersistentModelRepository<I extends Serializable, E extends IBasePersistenceModel<I>>
        extends IDefaultGenericReadOnlyRepository<I, E>,
        IBasePersistentModelRepository<I, E>, IBaseJpaRepository {

    default CriteriaBuilder createCriteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }

    default void applyRootCondition(
            CriteriaBuilder criteriaBuilder,
            IJpaCriteriaFromProvider fromProvider,
            CriteriaQuery<?> query, // don't use E cause it might be count query
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
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    default List<E> getListFromCriteria(TypedQuery<E> query) {
        return query.getResultList();
    }

    default <QueryResult> CriteriaQuery<QueryResult> createQuery(CriteriaBuilder criteriaBuilder, Class<QueryResult> queryResultClass) {
        return criteriaBuilder.createQuery(queryResultClass);
    }

    default String getEntityTableName() {
        // TODO read table name from metadata
        return getPersistentModelClass().getAnnotation(Table.class).name();
    }

}
