package ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.advancedearch;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.business.apis.advancedsearch.layerimpl.server.IBaseImplementedAdvancedSearchApi;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineDbOpMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionOneImpl;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionTwoImpl;
import ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.ro.IBaseImplementedMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.apis.search.advanced.server.ro.IBaseAdvancedSearchDao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

public interface IBaseImplementedMultiDaoAdvancedSearchApi<E extends IBaseEntity<Id>, SO extends IBaseSearchObject,
        Id extends Serializable, Category, Dao extends IBaseAdvancedSearchDao<E, SO, Id>>
        extends IBaseImplementedMultiDaoReadOnlyApi<E, Id, Category, Dao>, IBaseImplementedAdvancedSearchApi<E, SO, Id,
        Dao> {
    Category getCategoryForSearchObject(SO searchObject);

    @Override
    default Long countBySearchObject(SO searchObject) throws BaseVaselineServerException {
        Method countBySearchObjectMethod = getDeclaredMethod(IBaseImplementedMultiDaoAdvancedSearchApi.class, "countBySearchObject",
                IBaseSearchObject.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                getClass(), countBySearchObjectMethod, searchObject,
                so -> getDaoFor(getCategoryForSearchObject(so)).countBySearchObject(so)
                , VaselineDbOpMetadata.READ_ONLY
        ));
    }

    @Override
    default List<E> searchBySearchObject(SO searchObject) throws BaseVaselineServerException {
        Method searchBySearchObjectMethod = getDeclaredMethod(IBaseImplementedMultiDaoAdvancedSearchApi.class, "searchBySearchObject",
                IBaseSearchObject.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                getClass(), searchBySearchObjectMethod, searchObject, so -> {
            List<E> list = getDaoFor(getCategoryForSearchObject(so)).searchBySearchObject(so);
            postGetList(list);
            return list;
        }, VaselineDbOpMetadata.READ_ONLY));
    }

    @Override
    default List<E> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseVaselineServerException {
        Method searchBySearchObjectMethod = getDeclaredMethod(IBaseImplementedMultiDaoAdvancedSearchApi.class, "searchBySearchObject",
                IBaseSearchObject.class, PagingDto.class);
        return doBusinessAction(new BusinessFunctionTwoImpl<>(
                getClass(), searchBySearchObjectMethod, searchObject, pagingDto, (so, pd) -> {
            List<E> list = getDaoFor(getCategoryForSearchObject(so)).searchBySearchObject(so, pd);
            postGetList(list);
            return list;
        }, VaselineDbOpMetadata.READ_ONLY));
    }

    @Override
    default IVaselineDataScroller<E> scrollBySearchObject(SO searchObject, List<SortDto> sortList) throws
            BaseVaselineServerException{
        Method scrollBySearchObjectMethod = getDeclaredMethod(IBaseImplementedMultiDaoAdvancedSearchApi.class, "scrollBySearchObject",
                IBaseSearchObject.class, List.class);
        return doBusinessAction(new BusinessFunctionTwoImpl<>(
                getClass(), scrollBySearchObjectMethod, searchObject, sortList, (so, sl) -> {
            IVaselineDataScroller<E> scroller = getDaoFor(getCategoryForSearchObject(so)).
                    scrollBySearchObject(so, sl);
            scroller.addAfterFetchObject(this::postGet);
            return scroller;
        }, VaselineDbOpMetadata.READ_ONLY));
    }
}
