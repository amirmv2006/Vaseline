package ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.simplesearch;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.ro.IBaseImplementedMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.business.apis.simplesearch.layerimpl.server.IBaseImplementedSimpleSearchApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.simple.server.ro.IBaseSimpleSearchDao;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedMultiDaoSimpleSearchApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends
        Serializable, Category, Dao extends IBaseSimpleSearchDao<E, D, Id>>
        extends IBaseImplementedMultiDaoReadOnlyApi<E, Id, Category, Dao>, IBaseImplementedSimpleSearchApi<E, D, Id,
        Dao> {

    Category getCategoryForDto(D dto);

    @Override
    default Long countByExample(D example) throws BaseVaselineServerException {
        return getDaoFor(getCategoryForDto(example)).countByExample(example);
    }

    @Override
    default List<E> searchByExample(D example) throws BaseVaselineServerException {
        List<E> list = getDaoFor(getCategoryForDto(example)).searchByExample(example);
        postGetList(list);
        return list;
    }

    @Override
    default List<E> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineServerException {
        List<E> list = getDaoFor(getCategoryForDto(example)).searchByExample(example, pagingDto);
        postGetList(list);
        return list;
    }

    @Override
    default IVaselineDataScroller<E> scrollByExample(D example, List<SortDto> sortList) throws BaseVaselineServerException {
        IVaselineDataScroller<E> scroller = getDaoFor(getCategoryForDto(example)).scrollByExample(example, sortList);
        scroller.addAfterFetchObject(new BaseCallbackImpl<E, Void>() {
            @Override
            public void onSuccess(E result) throws Exception {
                postGet(result);
            }
        });
        return scroller;
    }
}
