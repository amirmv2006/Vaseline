package ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.base;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.base.IBaseDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import java.util.List;

public interface IBaseHibernateDao
        extends IBaseDao {

    SessionFactory getSessionFactory();

    default Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    default Criteria paginateCriteria(Criteria criteria,
                                      final PagingDto paginationObject) {
        List<SortDto> sortList = paginationObject.getSortList();
        if (sortList == null || sortList.isEmpty()) {
            criteria.addOrder(Order.desc("id"));
        } else {
            for (SortDto sortDto : sortList) {
                criteria.addOrder(sortDto.getAscending() ? Order.asc(sortDto
                        .getPropertyName()) : Order.desc(sortDto.getPropertyName()));
            }
        }
        if (paginationObject.getPageNumber() != null && paginationObject.getPageSize() != null) {
            return criteria.setMaxResults(paginationObject.getPageSize())
                    .setFirstResult(
                            paginationObject.getPageNumber()
                                    * paginationObject.getPageSize());
        } else {
            return criteria;
        }
    }

    default Long getCountResult(Criteria criteria) {
        Number uniqueResult = (Number) criteria.uniqueResult();
        if (uniqueResult == null) {
            return 0L;
        }
        return uniqueResult.longValue();
    }

    default DetachedCriteria getCountCriteria(DetachedCriteria detCriteria) {
        return detCriteria.setProjection(Projections.rowCount());
    }

}