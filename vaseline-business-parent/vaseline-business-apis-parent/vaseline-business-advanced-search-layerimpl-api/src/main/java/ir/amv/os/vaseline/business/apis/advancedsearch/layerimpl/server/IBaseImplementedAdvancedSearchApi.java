package ir.amv.os.vaseline.business.apis.advancedsearch.layerimpl.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.business.apis.advancedsearch.layer.server.IBaseAdvancedSearchApi;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.apis.search.advanced.server.ro.IBaseAdvancedSearchDao;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedAdvancedSearchApi<E extends IBaseEntity<Id>, SO extends IBaseSearchObject, Id extends Serializable>
        extends IBaseAdvancedSearchApi<E, SO, Id>, IBaseImplementedReadOnlyApi<E, Id> {

    IBaseAdvancedSearchDao<E, SO, Id> getAdvancedSearchDao();

    default Long countBySearchObject(SO searchObject) throws BaseVaselineServerException {
        return getAdvancedSearchDao().countBySearchObject(searchObject);
    }

    default List<E> searchBySearchObject(SO searchObject) throws BaseVaselineServerException {
        List<E> searchByExample = getAdvancedSearchDao().searchBySearchObject(searchObject);
        if (searchByExample != null) {
            for (E entity : searchByExample) {
                postGet(entity);
            }
        }
        return searchByExample;
    }

    default IVaselineDataScroller<E> scrollBySearchObject(SO searchObject, List<SortDto> sortList) throws
            BaseVaselineServerException {
        IVaselineDataScroller<E> scroller = getAdvancedSearchDao().scrollBySearchObject(searchObject, sortList);
        scroller.addAfterFetchObject(new BaseCallbackImpl<E, Void>() {
            @Override
            public void onSuccess(E result) throws Exception {
                postGet(result);
            }
        });
        return scroller;
    }

    default List<E> searchBySearchObject(SO searchObject, PagingDto pagingDto)
            throws BaseVaselineServerException {
        List<E> searchByExample = getAdvancedSearchDao().searchBySearchObject(searchObject, pagingDto);
        if (searchByExample != null) {
            for (E entity : searchByExample) {
                postGet(entity);
            }
        }
        return searchByExample;
    }
}
