package ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.advancedearch;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.notsupported.VaselineFeatureNotSupportedException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.business.apis.advancedsearch.layerimpl.server.IBaseImplementedAdvancedSearchApi;
import ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.ro.IBaseImplementedMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.apis.search.advanced.server.ro.IBaseAdvancedSearchDao;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedMultiDaoAdvancedSearchApi<E extends IBaseEntity<Id>, SO extends IBaseSearchObject,
        Id extends Serializable, Category>
        extends IBaseImplementedMultiDaoReadOnlyApi<E, Id, Category>, IBaseImplementedAdvancedSearchApi<E, SO, Id> {
    @Override
    default IBaseAdvancedSearchDao<E, SO, Id> getAdvancedSearchDao() {
        throw new VaselineFeatureNotSupportedException();
    }

    IBaseAdvancedSearchDao<E, SO, Id> getAdvancedSearchDaoFor(SO searchObject);

    @Override
    default Long countBySearchObject(SO searchObject) throws BaseVaselineServerException {
        return getAdvancedSearchDaoFor(searchObject).countBySearchObject(searchObject);
    }

    @Override
    default List<E> searchBySearchObject(SO searchObject) throws BaseVaselineServerException {
        List<E> list = getAdvancedSearchDaoFor(searchObject).searchBySearchObject(searchObject);
        postGetList(list);
        return list;
    }

    @Override
    default List<E> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseVaselineServerException {
        List<E> list = getAdvancedSearchDaoFor(searchObject).searchBySearchObject(searchObject, pagingDto);
        postGetList(list);
        return list;
    }

    @Override
    default IVaselineDataScroller<E> scrollBySearchObject(SO searchObject, List<SortDto> sortList) throws
            BaseVaselineServerException{
        IVaselineDataScroller<E> scroller = getAdvancedSearchDao().scrollBySearchObject(searchObject, sortList);
        scroller.addAfterFetchObject(new BaseCallbackImpl<E, Void>() {
            @Override
            public void onSuccess(E result) throws Exception {
                postGet(result);
            }
        });
        return scroller;
    }
}
