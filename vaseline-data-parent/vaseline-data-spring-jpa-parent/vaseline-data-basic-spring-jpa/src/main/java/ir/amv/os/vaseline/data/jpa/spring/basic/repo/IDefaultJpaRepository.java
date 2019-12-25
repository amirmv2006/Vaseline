package ir.amv.os.vaseline.data.jpa.spring.basic.repo;

import ir.amv.os.vaseline.data.basic.api.base.IBaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public interface IDefaultJpaRepository
        extends IBaseRepository {

    EntityManager getEntityManager();

    default Long getCountResult(CriteriaQuery<Number> query) {
        Number singleResult = getEntityManager().createQuery(query).getSingleResult();
        if (singleResult == null) {
            singleResult = 0L;
        }
        return singleResult.longValue();
    }

    default CriteriaBuilder createCriteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }

    default <X> TypedQuery<X> getTypedQuery(EntityManager entityManager, CriteriaQuery<X> criteriaQuery) {
        return getEntityManager().createQuery(criteriaQuery);
    }

    default <QueryResult> CriteriaQuery<QueryResult> createQuery(CriteriaBuilder criteriaBuilder, Class<QueryResult> queryResultClass) {
        return criteriaBuilder.createQuery(queryResultClass);
    }

}
