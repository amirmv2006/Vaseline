package ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.IDefaultHibernateReadOnlyDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface IHibernateFetchProvider<I extends Serializable, E extends IBaseBusinessModel<I>> {

    default Long count(IDefaultHibernateReadOnlyDao<I, E> dao, IDetachedCriteriaPrunerFunctionalInterface criteriaPruner) {
        DetachedCriteria detachedCriteria = dao.createCriteria();
        criteriaPruner.pruneCriteria(detachedCriteria);
        detachedCriteria = dao.getCountCriteria(detachedCriteria);
        Criteria criteria = dao.getCriteriaFromDetachedCriteria(detachedCriteria);
        return dao.getCountResult(criteria);
    }

    default List<E> list(IDefaultHibernateReadOnlyDao<I, E> dao, IDetachedCriteriaPrunerFunctionalInterface criteriaPruner) {
        DetachedCriteria detachedCriteria = dao.createCriteria();
        criteriaPruner.pruneCriteria(detachedCriteria);
        Criteria criteria = dao.getCriteriaFromDetachedCriteria(detachedCriteria);
        return dao.getListFromCriteria(criteria);
    }

    default List<E> page(IDefaultHibernateReadOnlyDao<I, E> dao, IDetachedCriteriaPrunerFunctionalInterface criteriaPruner, PagingDto pagingDto) {
        DetachedCriteria detachedCriteria = dao.createCriteria();
        criteriaPruner.pruneCriteria(detachedCriteria);
        Criteria criteria = dao.getCriteriaFromDetachedCriteria(detachedCriteria);
        dao.paginateCriteria(criteria, pagingDto);
        return dao.getListFromCriteria(criteria);
    }

    default IVaselineDataScroller<E> scroll(IDefaultHibernateReadOnlyDao<I, E> dao, IDetachedCriteriaPrunerFunctionalInterface criteriaPruner, List<SortDto> sortList) {
        DetachedCriteria detachedCriteria = dao.createCriteria();
        criteriaPruner.pruneCriteria(detachedCriteria);
        Criteria criteria = dao.getCriteriaFromDetachedCriteria(detachedCriteria);
        dao.paginateCriteria(criteria, new PagingDto(sortList, null, null));
        return dao.scrollCriteria(criteria);
    }

    default E unique(IDefaultHibernateReadOnlyDao<I, E> dao, IDetachedCriteriaPrunerFunctionalInterface criteriaPruner) {
        DetachedCriteria detachedCriteria = dao.createCriteria();
        criteriaPruner.pruneCriteria(detachedCriteria);
        Criteria criteria = dao.getCriteriaFromDetachedCriteria(detachedCriteria);
        return dao.getEntityFromCriteria(criteria);
    }
}
