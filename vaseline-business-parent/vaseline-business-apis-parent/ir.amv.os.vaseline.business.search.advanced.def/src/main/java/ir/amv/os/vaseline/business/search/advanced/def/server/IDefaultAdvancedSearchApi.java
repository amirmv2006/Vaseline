package ir.amv.os.vaseline.business.search.advanced.def.server;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.basic.def.server.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.business.search.advanced.api.server.IBaseAdvancedSearchApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.ro.IBaseAdvancedSearchDao;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface IDefaultAdvancedSearchApi<E extends IBaseEntity<Id>, SO extends IBaseSearchObject,
        Id extends Serializable, Dao extends IBaseAdvancedSearchDao<Id, E, SO>>
        extends IBaseAdvancedSearchApi<E, SO, Id>, IDefaultReadOnlyApi<E, Id, Dao> {

    @Transactional
    default Long countBySearchObject(SO searchObject) throws BaseVaselineServerException {
        return getDao().countBySearchObject(searchObject);
    }

    @Transactional
    default List<E> searchBySearchObject(SO searchObject) throws BaseVaselineServerException {
        List<E> searchByExample = getDao().searchBySearchObject(searchObject);
        postGetList(searchByExample);
        return searchByExample;
    }

    @Transactional
    default IVaselineDataScroller<E> scrollBySearchObject(SO searchObject, List<SortDto> sortList) throws
            BaseVaselineServerException {
        IVaselineDataScroller<E> scroller = getDao().scrollBySearchObject(searchObject, sortList);
        scroller.addAfterFetchObject(this::postGet);
        return scroller;
    }

    @Transactional
    default List<E> searchBySearchObject(SO searchObject, PagingDto pagingDto)
            throws BaseVaselineServerException {
        List<E> searchByExample = getDao().searchBySearchObject(searchObject, pagingDto);
        postGetList(searchByExample);
        return searchByExample;
    }
}
