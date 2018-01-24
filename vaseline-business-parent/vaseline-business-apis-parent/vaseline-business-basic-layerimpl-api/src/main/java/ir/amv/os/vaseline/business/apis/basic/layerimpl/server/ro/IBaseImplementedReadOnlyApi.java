package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineDbOpMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionOneImpl;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionZeroImpl;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

public interface IBaseImplementedReadOnlyApi<E extends IBaseEntity<Id>, Id extends Serializable, Dao extends
        IBaseReadOnlyDao<E, Id>>
        extends IBaseReadOnlyApi<E, Id>, IBaseImplementedEntityReadOnlyApi<E> {

    Dao getDao();

    @Transactional
    default E getById(Id id) throws BaseVaselineServerException {
        Method getByIdMethod = getDeclaredMethod(IBaseImplementedReadOnlyApi.class, "getById", Serializable.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                        getClass(), getByIdMethod, id, id1 -> {
                    E findById = getDao().getById(id1);
                    postGet(findById);
                    return findById;
                }, VaselineDbOpMetadata.READ_ONLY)
        );
    }

    @Transactional
    default Long countAllApproximately() throws BaseVaselineServerException {
        Method countAllApproximatelyMethod = getDeclaredMethod(IBaseImplementedReadOnlyApi.class, "countAllApproximately");
        return doBusinessAction(new BusinessFunctionZeroImpl<>(
                getClass(), countAllApproximatelyMethod, () -> getDao().countAllApproximately(),
                VaselineDbOpMetadata.READ_ONLY
        ));
    }

    @Transactional
    default Long countAll() throws BaseVaselineServerException {
        Method countAllMethod = getDeclaredMethod(IBaseImplementedReadOnlyApi.class, "countAll");
        return doBusinessAction(new BusinessFunctionZeroImpl<>(
                getClass(), countAllMethod, () -> getDao().countAll(), VaselineDbOpMetadata.READ_ONLY
        ));
    }

    @Transactional
    default List<E> getAll() throws BaseVaselineServerException {
        Method getAllMethod = getDeclaredMethod(IBaseImplementedReadOnlyApi.class, "getAll");
        return doBusinessAction(new BusinessFunctionZeroImpl<>(
                getClass(), getAllMethod, () -> {
            List<E> all = getDao().getAll();
            postGetList(all);
            return all;
        }, VaselineDbOpMetadata.READ_ONLY)
        );
    }

    @Transactional
    default IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseVaselineServerException {
        Method scrollAllMethod = getDeclaredMethod(IBaseImplementedReadOnlyApi.class, "scrollAll", List.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                        getClass(), scrollAllMethod, sortList, (sl) -> {
                    IVaselineDataScroller<E> scroller = getDao().scrollAll(sl);
                    scroller.addAfterFetchObject(this::postGet);
                    return scroller;
                }, VaselineDbOpMetadata.READ_ONLY)
        );
    }

    @Transactional
    default List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException {
        Method getAllPagedMethod = getDeclaredMethod(IBaseImplementedReadOnlyApi.class, "getAll", PagingDto.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                        getClass(), getAllPagedMethod, pagingDto, (pd) -> {
                    List<E> all = getDao().getAll(pd);
                    postGetList(all);
                    return all;
                }, VaselineDbOpMetadata.READ_ONLY)
        );
    }

    default void postGetList(final List<E> list) throws BaseVaselineServerException {
        if (list != null) {
            for (E entity : list) {
                postGet(entity);
            }
        }
    }

}
