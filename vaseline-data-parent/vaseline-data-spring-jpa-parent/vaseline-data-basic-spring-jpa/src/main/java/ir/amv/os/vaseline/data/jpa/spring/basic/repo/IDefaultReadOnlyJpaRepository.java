package ir.amv.os.vaseline.data.jpa.spring.basic.repo;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.basics.spring.core.utils.reflection.GenericUtils;
import ir.amv.os.vaseline.data.basic.api.base.IBaseReadOnlyRepository;
import ir.amv.os.vaseline.data.jpa.spring.basic.projection.IJpaCriteriaFromProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.List;

public interface IDefaultReadOnlyJpaRepository<I extends Serializable, E extends IBasePersistenceModel<I>>
        extends IBaseReadOnlyRepository<I, E>, IDefaultJpaRepository {

    <F extends E> Page<F> readPage(
            TypedQuery<F> query,
            final Class<F> domainClass,
            Pageable pageable,
            @Nullable Specification<F> spec);

    default void applyRootCondition(
            CriteriaBuilder criteriaBuilder,
            IJpaCriteriaFromProvider fromProvider,
            CriteriaQuery<?> query, // don't use E cause it might be count query
            Predicate rootCondition) {
        if (rootCondition != null) {
            query.where(rootCondition);
        }
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

    default String getEntityTableName() {
        // TODO read table name from metadata
        return getPersistentModelClass().getAnnotation(Table.class).name();
    }

    @Override
    default Class<E> getPersistentModelClass() {
        return GenericUtils.getGeneric(getClass(), IDefaultReadOnlyJpaRepository.class, 1);
    }

}
