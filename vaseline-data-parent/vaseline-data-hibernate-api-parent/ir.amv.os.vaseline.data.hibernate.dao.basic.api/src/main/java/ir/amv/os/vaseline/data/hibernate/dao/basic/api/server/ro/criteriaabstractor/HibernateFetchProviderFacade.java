package ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.IDefaultHibernateReadOnlyDao;

import java.io.Serializable;
import java.util.List;

public class HibernateFetchProviderFacade<I extends Serializable, E extends IBaseEntity<I>> {

    private IHibernateFetchProvider<I, E> abstractor;
    private IDefaultHibernateReadOnlyDao<I, E> dao;
    private IDetachedCriteriaPrunerFunctionalInterface criteriaPruner;

    public HibernateFetchProviderFacade(
            IHibernateFetchProvider<I, E> fetchProvider,
            IDefaultHibernateReadOnlyDao<I, E> dao,
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
