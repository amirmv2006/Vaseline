package ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.simplesearch;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineDbOpMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionOneImpl;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionTwoImpl;
import ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.ro.IBaseImplementedMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.business.apis.simplesearch.layerimpl.server.IBaseImplementedSimpleSearchApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.simple.server.ro.IBaseSimpleSearchDao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

public interface IBaseImplementedMultiDaoSimpleSearchApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends
        Serializable, Category, Dao extends IBaseSimpleSearchDao<E, D, Id>>
        extends IBaseImplementedMultiDaoReadOnlyApi<E, Id, Category, Dao>, IBaseImplementedSimpleSearchApi<E, D, Id,
        Dao> {

    Category getCategoryForDto(D dto);

    @Override
    default Long countByExample(D example) throws BaseVaselineServerException {
        Method countByExampleMethod = getDeclaredMethod(IBaseImplementedMultiDaoSimpleSearchApi.class,
                "countByExample", IBaseDto.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                getClass(), countByExampleMethod, example,
                ex -> getDaoFor(getCategoryForDto(ex)).countByExample(ex),
                VaselineDbOpMetadata.READ_ONLY
        ));
    }

    @Override
    default List<E> searchByExample(D example) throws BaseVaselineServerException {
        Method searchByExampleMethod = getDeclaredMethod(IBaseImplementedMultiDaoSimpleSearchApi.class,
                "searchByExample", IBaseDto.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                getClass(), searchByExampleMethod, example, ex -> {
            List<E> list = getDaoFor(getCategoryForDto(ex)).searchByExample(ex);
            postGetList(list);
            return list;
        }, VaselineDbOpMetadata.READ_ONLY
        ));
    }

    @Override
    default List<E> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineServerException {
        Method searchByExampleMethod = getDeclaredMethod(IBaseImplementedMultiDaoSimpleSearchApi.class,
                "searchByExample", IBaseDto.class, PagingDto.class);
        return doBusinessAction(new BusinessFunctionTwoImpl<>(
                getClass(), searchByExampleMethod, example, pagingDto, (e, p) -> {
            List<E> list = getDaoFor(getCategoryForDto(e)).searchByExample(e, p);
            postGetList(list);
            return list;
        }, VaselineDbOpMetadata.READ_ONLY
        ));
    }

    @Override
    default IVaselineDataScroller<E> scrollByExample(D example, List<SortDto> sortList) throws BaseVaselineServerException {
        Method scrollByExampleMethod = getDeclaredMethod(IBaseImplementedMultiDaoSimpleSearchApi.class, "scrollByExample", IBaseDto
                .class, List.class);
        return doBusinessAction(new BusinessFunctionTwoImpl<>(
                getClass(), scrollByExampleMethod, example, sortList, (ex, sl) -> {
            IVaselineDataScroller<E> scroller = getDaoFor(getCategoryForDto(ex)).scrollByExample(ex, sl);
            scroller.addAfterFetchObject(this::postGet);
            return scroller;
        }, VaselineDbOpMetadata.READ_ONLY
        ));
    }
}
