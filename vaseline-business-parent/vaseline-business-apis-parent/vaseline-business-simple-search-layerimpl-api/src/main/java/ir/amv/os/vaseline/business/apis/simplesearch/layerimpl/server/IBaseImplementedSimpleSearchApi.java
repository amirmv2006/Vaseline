package ir.amv.os.vaseline.business.apis.simplesearch.layerimpl.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineDbOpMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionOneImpl;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionTwoImpl;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.business.apis.simplesearch.layer.server.IBaseSimpleSearchApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.simple.server.ro.IBaseSimpleSearchDao;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

public interface IBaseImplementedSimpleSearchApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>,
        Id extends Serializable, Dao extends IBaseSimpleSearchDao<E, D, Id>>
        extends IBaseSimpleSearchApi<E, D, Id>, IBaseImplementedReadOnlyApi<E, Id, Dao> {

    @Transactional
    default Long countByExample(D example) throws BaseVaselineServerException {
        Method countByExampleMethod = getDeclaredMethod(IBaseImplementedSimpleSearchApi.class, "countByExample",
                IBaseDto.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                getClass(), countByExampleMethod, example, e -> getDao().countByExample(e), VaselineDbOpMetadata.READ_ONLY
        ));
    }

    @Transactional
    default List<E> searchByExample(D example) throws BaseVaselineServerException {
        Method searchByExampleMethod = getDeclaredMethod(IBaseImplementedSimpleSearchApi.class, "searchByExample",
                IBaseDto.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                getClass(), searchByExampleMethod, example, e -> {
            List<E> searchByExample = getDao().searchByExample(e);
            postGetList(searchByExample);
            return searchByExample;
        }, VaselineDbOpMetadata.READ_ONLY));
    }

    @Transactional
    default IVaselineDataScroller<E> scrollByExample(D example, List<SortDto> sortList) throws BaseVaselineServerException {
        Method scrollByExampleMethod = getDeclaredMethod(IBaseImplementedSimpleSearchApi.class, "scrollByExample", IBaseDto.class,
                List.class);
        return doBusinessAction(new BusinessFunctionTwoImpl<>(
                getClass(), scrollByExampleMethod, example, sortList, (e, sl) -> {
            IVaselineDataScroller<E> scroller = getDao().scrollByExample(e, sl);
            scroller.addAfterFetchObject(this::postGet);
            return scroller;
        }, VaselineDbOpMetadata.READ_ONLY));
    }

    @Transactional
    default List<E> searchByExample(D example, PagingDto pagingDto)
            throws BaseVaselineServerException {
        Method searchByExampleMethod = getDeclaredMethod(IBaseImplementedSimpleSearchApi.class, "searchByExample", IBaseDto.class,
                PagingDto.class);
        return doBusinessAction(new BusinessFunctionTwoImpl<>(
                getClass(), searchByExampleMethod, example, pagingDto, (e, pd) -> {
            List<E> searchByExample = getDao().searchByExample(e, pd);
            postGetList(searchByExample);
            return searchByExample;
        }, VaselineDbOpMetadata.READ_ONLY));
    }
}
