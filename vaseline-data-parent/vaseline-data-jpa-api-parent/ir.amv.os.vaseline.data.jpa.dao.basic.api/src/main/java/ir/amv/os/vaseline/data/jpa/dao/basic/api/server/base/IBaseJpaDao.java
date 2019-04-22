package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.base;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.base.IBaseDao;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.projection.IJpaCriteriaFromProvider;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface IBaseJpaDao
        extends IBaseDao {

    EntityManager getEntityManager();

    // FIXME remove?
    default Predicate andAllPredicatesList(CriteriaBuilder cb, List<Predicate> predicates) {
        return predicates.size() == 1 ? predicates.get(0) : cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    default <Q> TypedQuery<Q> paginateCriteria(
            EntityManager em,
            CriteriaBuilder criteriaBuilder,
            IJpaCriteriaFromProvider fromProvider,
            CriteriaQuery<Q> query,
            final PagingDto paginationObject
    ) {
        List<SortDto> sortList = paginationObject.getSortList();
        if (sortList == null || sortList.isEmpty()) {
            query.orderBy(criteriaBuilder.desc(fromProvider.getCriteriaParentProjection("id", null)));
        } else {
            List<Order> orderList = new ArrayList<Order>(sortList.size());
            for (SortDto sortDto : sortList) {
                Path from = fromProvider.getCriteriaParentProjection(sortDto.getPropertyName(), null);
                orderList.add(sortDto.getAscending() ? criteriaBuilder.asc(from) : criteriaBuilder.desc(from));
            }
            query.orderBy(orderList);
        }
        TypedQuery<Q> typedQuery = em.createQuery(query);
        if (paginationObject.getPageNumber() != null && paginationObject.getPageSize() != null) {
            return typedQuery.setMaxResults(paginationObject.getPageSize())
                    .setFirstResult(
                            paginationObject.getPageNumber()
                                    * paginationObject.getPageSize());
        } else {
            return typedQuery;
        }
    }

    default Long getCountResult(CriteriaQuery<Number> query) {
        Number singleResult = getEntityManager().createQuery(query).getSingleResult();
        if (singleResult == null) {
            singleResult = 0L;
        }
        return singleResult.longValue();
    }
}
