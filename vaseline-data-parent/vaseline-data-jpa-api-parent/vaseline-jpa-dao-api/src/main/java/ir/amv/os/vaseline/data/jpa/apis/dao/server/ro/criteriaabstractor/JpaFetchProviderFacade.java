package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.IBaseImplementedJpaReadOnlyDao;

import java.io.Serializable;
import java.util.List;

public class JpaFetchProviderFacade<E extends IBaseEntity<Id>, Id extends Serializable> {

    private IJpaFetchProvider<E, Id> abstractor;
    private IBaseImplementedJpaReadOnlyDao<E, Id> dao;
    private IJpaCriteriaPrunerFunctionalInterface criteriaPruner;

    public JpaFetchProviderFacade(
            IJpaFetchProvider<E, Id> fetchProvider,
            IBaseImplementedJpaReadOnlyDao<E, Id> dao,
            IJpaCriteriaPrunerFunctionalInterface criteriaPruner) {
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
        return (E) abstractor.unique(dao, criteriaPruner);
    }
}
