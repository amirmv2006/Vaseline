package ir.amv.os.vaseline.business.search.advanced.def.server;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.business.basic.def.server.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.business.search.advanced.api.server.IBaseAdvancedSearchApi;
import ir.amv.os.vaseline.data.dao.basic.api.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.ro.IBaseAdvancedSearchRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface IDefaultAdvancedSearchApi<I extends Serializable, E extends IBaseBusinessModel<I>, SO extends IBaseSearchObject,
        Dao extends IBaseAdvancedSearchRepository<I, E, SO>>
        extends IBaseAdvancedSearchApi<I, E, SO>, IDefaultReadOnlyApi<I, E, Dao> {

    @Transactional
    default Long countBySearchObject(SO searchObject) throws BaseBusinessException {
        return getDao().countBySearchObject(searchObject);
    }

    @Transactional
    default List<E> searchBySearchObject(SO searchObject) throws BaseBusinessException {
        List<E> searchByExample = getDao().searchBySearchObject(searchObject);
        postGetList(searchByExample);
        return searchByExample;
    }

    @Transactional
    default IVaselineDataScroller<E> scrollBySearchObject(SO searchObject, List<SortDto> sortList) throws
            BaseBusinessException {
        IVaselineDataScroller<E> scroller = getDao().scrollBySearchObject(searchObject, sortList);
        scroller.addAfterFetchObject(this::postGet);
        return scroller;
    }

    @Transactional
    default List<E> searchBySearchObject(SO searchObject, PagingDto pagingDto)
            throws BaseBusinessException {
        List<E> searchByExample = getDao().searchBySearchObject(searchObject, pagingDto);
        postGetList(searchByExample);
        return searchByExample;
    }
}
