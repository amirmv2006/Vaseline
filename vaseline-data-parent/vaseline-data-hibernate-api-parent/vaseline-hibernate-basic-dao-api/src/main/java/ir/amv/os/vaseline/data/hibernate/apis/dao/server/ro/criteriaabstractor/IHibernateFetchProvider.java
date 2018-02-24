package ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.IBaseImplementedHibernateReadOnlyDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface IHibernateFetchProvider<E extends IBaseEntity<Id>, Id extends Serializable> {

    default Long count(IBaseImplementedHibernateReadOnlyDao<E, Id> dao, IDetachedCriteriaPrunerFunctionalInterface criteriaPruner) {
        DetachedCriteria detachedCriteria = dao.createCriteria();
        criteriaPruner.pruneCriteria(detachedCriteria);
        detachedCriteria = dao.getCountCriteria(detachedCriteria);
        Criteria criteria = dao.getCriteriaFromDetachedCriteria(detachedCriteria);
        return dao.getCountResult(criteria);
    }

    default List<E> list(IBaseImplementedHibernateReadOnlyDao<E, Id> dao, IDetachedCriteriaPrunerFunctionalInterface criteriaPruner) {
        DetachedCriteria detachedCriteria = dao.createCriteria();
        criteriaPruner.pruneCriteria(detachedCriteria);
        Criteria criteria = dao.getCriteriaFromDetachedCriteria(detachedCriteria);
        return dao.getListFromCriteria(criteria);
    }

    default List<E> page(IBaseImplementedHibernateReadOnlyDao<E, Id> dao, IDetachedCriteriaPrunerFunctionalInterface criteriaPruner, PagingDto pagingDto) {
        DetachedCriteria detachedCriteria = dao.createCriteria();
        criteriaPruner.pruneCriteria(detachedCriteria);
        Criteria criteria = dao.getCriteriaFromDetachedCriteria(detachedCriteria);
        dao.paginateCriteria(criteria, pagingDto);
        return dao.getListFromCriteria(criteria);
    }

    default IVaselineDataScroller<E> scroll(IBaseImplementedHibernateReadOnlyDao<E, Id> dao, IDetachedCriteriaPrunerFunctionalInterface criteriaPruner, List<SortDto> sortList) {
        DetachedCriteria detachedCriteria = dao.createCriteria();
        criteriaPruner.pruneCriteria(detachedCriteria);
        Criteria criteria = dao.getCriteriaFromDetachedCriteria(detachedCriteria);
        dao.paginateCriteria(criteria, new PagingDto(sortList, null, null));
        return dao.scrollCriteria(criteria);
    }

    default E unique(IBaseImplementedHibernateReadOnlyDao<E, Id> dao, IDetachedCriteriaPrunerFunctionalInterface criteriaPruner) {
        DetachedCriteria detachedCriteria = dao.createCriteria();
        criteriaPruner.pruneCriteria(detachedCriteria);
        Criteria criteria = dao.getCriteriaFromDetachedCriteria(detachedCriteria);
        return dao.getEntityFromCriteria(criteria);
    }
}
