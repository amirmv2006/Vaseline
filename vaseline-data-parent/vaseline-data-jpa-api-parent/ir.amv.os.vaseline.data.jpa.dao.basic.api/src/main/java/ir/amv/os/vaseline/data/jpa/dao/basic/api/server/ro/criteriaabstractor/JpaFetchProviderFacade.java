package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaReadOnlyDao;

import java.io.Serializable;
import java.util.List;

public class JpaFetchProviderFacade<Id extends Serializable, E extends IBaseEntity<Id>> {

    private IJpaFetchProvider<Id, E> abstractor;
    private IDefaultJpaReadOnlyDao<Id, E> dao;
    private IJpaCriteriaPrunerFunctionalInterface criteriaPruner;

    public JpaFetchProviderFacade(
            IJpaFetchProvider<Id, E> fetchProvider,
            IDefaultJpaReadOnlyDao<Id, E> dao,
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
