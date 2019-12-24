package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.base;

import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.impl.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.base.IBaseRepository;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.projection.IJpaCriteriaFromProvider;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import java.util.ArrayList;
import java.util.List;

public interface IBaseJpaRepository
        extends IBaseRepository {

    EntityManager getEntityManager();

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
