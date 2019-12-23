package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaReadOnlyRepository;

import java.io.Serializable;
import java.util.List;

public class JpaFetchProviderFacade<Id extends Serializable, E extends IBaseBusinessModel<Id>> {

    private IJpaFetchProvider<Id, E> abstractor;
    private IDefaultJpaReadOnlyRepository<Id, E> dao;
    private IJpaCriteriaPrunerFunctionalInterface criteriaPruner;

    public JpaFetchProviderFacade(
            IJpaFetchProvider<Id, E> fetchProvider,
            IDefaultJpaReadOnlyRepository<Id, E> dao,
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
