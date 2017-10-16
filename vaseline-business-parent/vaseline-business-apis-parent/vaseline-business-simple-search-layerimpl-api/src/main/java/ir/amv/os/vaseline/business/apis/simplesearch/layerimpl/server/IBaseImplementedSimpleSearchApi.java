package ir.amv.os.vaseline.business.apis.simplesearch.layerimpl.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.business.apis.simplesearch.layer.server.IBaseSimpleSearchApi;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.simple.server.ro.IBaseSimpleSearchDao;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedSimpleSearchApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseSimpleSearchApi<E, D, Id>, IBaseImplementedReadOnlyApi<E, Id> {

    IBaseSimpleSearchDao<E, D, Id> getSimpleSearchDao();

    default Long countByExample(D example) throws BaseVaselineServerException {
        return getSimpleSearchDao().countByExample(example);
    }

    default List<E> searchByExample(D example) throws BaseVaselineServerException {
        List<E> searchByExample = getSimpleSearchDao().searchByExample(example);
        if (searchByExample != null) {
            for (E entity : searchByExample) {
                postGet(entity);
            }
        }
        return searchByExample;
    }

    default IVaselineDataScroller<E> scrollByExample(D example, List<SortDto> sortList) throws BaseVaselineServerException {
        IVaselineDataScroller<E> scroller = getSimpleSearchDao().scrollByExample(example, sortList);
        scroller.addAfterFetchObject(new BaseCallbackImpl<E, Void>() {
            @Override
            public void onSuccess(E result) throws Exception {
                postGet(result);
            }
        });
        return scroller;
    }

    default List<E> searchByExample(D example, PagingDto pagingDto)
            throws BaseVaselineServerException {
        List<E> searchByExample = getSimpleSearchDao().searchByExample(example, pagingDto);
        if (searchByExample != null) {
            for (E entity : searchByExample) {
                postGet(entity);
            }
        }
        return searchByExample;
    }
}
