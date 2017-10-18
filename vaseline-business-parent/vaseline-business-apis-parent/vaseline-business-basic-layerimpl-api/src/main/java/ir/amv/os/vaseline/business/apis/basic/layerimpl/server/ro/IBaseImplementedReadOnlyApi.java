package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.business.apis.basic.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedReadOnlyApi<E extends IBaseEntity<Id>, Id extends Serializable, Dao extends
        IBaseReadOnlyDao<E, Id>>
        extends IBaseReadOnlyApi<E, Id>, IBaseImplementedEntityReadOnlyApi<E> {

    Dao getDao();

    default E getById(Id id) throws BaseVaselineServerException {
        E findById = getDao().getById(id);
        postGet(findById);
        return findById;
    }

    default Long countAllApproximately() throws BaseVaselineServerException {
        return getDao().countAllApproximately();
    }

    default Long countAll() throws BaseVaselineServerException {
        return getDao().countAll();
    }

    default List<E> getAll() throws BaseVaselineServerException {
        List<E> all = getDao().getAll();
        postGetList(all);
        return all;
    }

    default IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseVaselineServerException {
        IVaselineDataScroller<E> scroller = getDao().scrollAll(sortList);
        scroller.addAfterFetchObject(new BaseCallbackImpl<E, Void>() {
            @Override
            public void onSuccess(E result) throws Exception {
                postGet(result);
            }
        });
        return scroller;
    }

    default List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException {
        List<E> all = getDao().getAll(pagingDto);
        postGetList(all);
        return all;
    }

    default void postGetList(final List<E> list) throws BaseVaselineServerException {
        if (list != null) {
            for (E entity : list) {
                postGet(entity);
            }
        }
    }

}
