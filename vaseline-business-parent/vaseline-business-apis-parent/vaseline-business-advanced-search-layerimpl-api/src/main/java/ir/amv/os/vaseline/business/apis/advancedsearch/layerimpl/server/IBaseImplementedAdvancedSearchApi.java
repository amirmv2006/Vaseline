package ir.amv.os.vaseline.business.apis.advancedsearch.layerimpl.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.business.apis.advancedsearch.layer.server.IBaseAdvancedSearchApi;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineDbOpMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionOneImpl;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionTwoImpl;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.apis.search.advanced.server.ro.IBaseAdvancedSearchDao;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

public interface IBaseImplementedAdvancedSearchApi<E extends IBaseEntity<Id>, SO extends IBaseSearchObject,
        Id extends Serializable, Dao extends IBaseAdvancedSearchDao<E, SO, Id>>
        extends IBaseAdvancedSearchApi<E, SO, Id>, IBaseImplementedReadOnlyApi<E, Id, Dao> {

    @Transactional
    default Long countBySearchObject(SO searchObject) throws BaseVaselineServerException {
        Method countBySearchObjectMethod = getDeclaredMethod(IBaseImplementedAdvancedSearchApi.class, "countBySearchObject",
                IBaseSearchObject.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                getClass(), countBySearchObjectMethod, searchObject, so -> getDao().countBySearchObject(so)
                , VaselineDbOpMetadata.READ_ONLY));
    }

    @Transactional
    default List<E> searchBySearchObject(SO searchObject) throws BaseVaselineServerException {
        Method searchBySearchObjectMethod = getDeclaredMethod(IBaseImplementedAdvancedSearchApi.class, "searchBySearchObject",
                IBaseSearchObject.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                getClass(), searchBySearchObjectMethod, searchObject, so -> {
            List<E> searchByExample = getDao().searchBySearchObject(so);
            postGetList(searchByExample);
            return searchByExample;
        }, VaselineDbOpMetadata.READ_ONLY));
    }

    @Transactional
    default IVaselineDataScroller<E> scrollBySearchObject(SO searchObject, List<SortDto> sortList) throws
            BaseVaselineServerException {
        Method scrollBySearchObjectMethod = getDeclaredMethod(IBaseImplementedAdvancedSearchApi.class, "scrollBySearchObject",
                IBaseSearchObject.class, List.class);
        return doBusinessAction(new BusinessFunctionTwoImpl<>(
                getClass(), scrollBySearchObjectMethod, searchObject, sortList, (so, sl) -> {
            IVaselineDataScroller<E> scroller = getDao().scrollBySearchObject(so, sl);
            scroller.addAfterFetchObject(this::postGet);
            return scroller;
        }, VaselineDbOpMetadata.READ_ONLY));
    }

    @Transactional
    default List<E> searchBySearchObject(SO searchObject, PagingDto pagingDto)
            throws BaseVaselineServerException {
        Method searchBySearchObjectMethod = getDeclaredMethod(IBaseImplementedAdvancedSearchApi.class, "searchBySearchObject",
                IBaseSearchObject.class, PagingDto.class);
        return doBusinessAction(new BusinessFunctionTwoImpl<>(
                getClass(), searchBySearchObjectMethod, searchObject, pagingDto, (so, pd) -> {
            List<E> searchByExample = getDao().searchBySearchObject(so, pd);
            postGetList(searchByExample);
            return searchByExample;
        }, VaselineDbOpMetadata.READ_ONLY));
    }
}
