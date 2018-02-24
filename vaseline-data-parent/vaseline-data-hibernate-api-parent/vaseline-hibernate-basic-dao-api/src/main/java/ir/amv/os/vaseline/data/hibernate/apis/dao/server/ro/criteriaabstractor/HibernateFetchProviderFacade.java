package ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.IBaseImplementedHibernateReadOnlyDao;

import java.io.Serializable;
import java.util.List;

public class HibernateFetchProviderFacade<E extends IBaseEntity<Id>, Id extends Serializable> {

    private IHibernateFetchProvider<E, Id> abstractor;
    private IBaseImplementedHibernateReadOnlyDao<E, Id> dao;
    private IDetachedCriteriaPrunerFunctionalInterface criteriaPruner;

    public HibernateFetchProviderFacade(
            IHibernateFetchProvider<E, Id> fetchProvider,
            IBaseImplementedHibernateReadOnlyDao<E, Id> dao,
            IDetachedCriteriaPrunerFunctionalInterface criteriaPruner) {
        this.abstractor = fetchProvider;
        this.dao = dao;
        this.criteriaPruner = criteriaPruner;
    }

    public Long count() {
        return abstractor.count(dao, criteriaPruner);
    }

    public List<E> list() {
        return abstractor.list(dao, criteriaPruner);
    }

    public List<E> page(PagingDto pagingDto) {
        return abstractor.page(dao, criteriaPruner, pagingDto);
    }

    public IVaselineDataScroller<E> scroll(List<SortDto> sortList) {
        return abstractor.scroll(dao, criteriaPruner, sortList);
    }

    public E unique() {
        return abstractor.unique(dao, criteriaPruner);
    }
}
