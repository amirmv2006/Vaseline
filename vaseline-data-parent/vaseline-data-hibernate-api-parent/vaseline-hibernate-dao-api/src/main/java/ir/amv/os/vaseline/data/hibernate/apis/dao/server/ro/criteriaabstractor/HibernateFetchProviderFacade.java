package ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.IBaseHibernateReadOnlyDao;

import java.io.Serializable;
import java.util.List;

public class HibernateFetchProviderFacade<E extends IBaseEntity<Id>, Id extends Serializable> {

    private IHibernateFetchProvider<E, Id> abstractor;
    private IBaseHibernateReadOnlyDao<E, Id> dao;
    private IDetachedCriteriaPrunerFunctionalInterface criteriaPruner;

    public HibernateFetchProviderFacade(
            IHibernateFetchProvider<E, Id> fetchProvider,
            IBaseHibernateReadOnlyDao<E, Id> dao,
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

    public IVaselineDataScroller<E> scroll() {
        return abstractor.scroll(dao, criteriaPruner);
    }

    public E unique() {
        return abstractor.unique(dao, criteriaPruner);
    }
}
