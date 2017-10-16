package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.business.apis.basic.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedReadOnlyApi<E extends IBaseEntity<Id>, Id extends Serializable>
        extends IBaseReadOnlyApi<E, Id>, IBaseImplementedEntityReadOnlyApi<E> {

    IBaseReadOnlyDao<E, Id> getReadDao();

    default E getById(Id id) throws BaseVaselineServerException {
        E findById = getReadDao().getById(id);
        postGet(findById);
        return findById;
    }

    default Long countAllApproximately() throws BaseVaselineServerException {
        return getReadDao().countAllApproximately();
    }

    default Long countAll() throws BaseVaselineServerException {
        return getReadDao().countAll();
    }

    default List<E> getAll() throws BaseVaselineServerException {
        List<E> all = getReadDao().getAll();
        postGetList(all);
        return all;
    }

    default IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseVaselineServerException {
        IVaselineDataScroller<E> scroller = getReadDao().scrollAll(sortList);
        scroller.addAfterFetchObject(new BaseCallbackImpl<E, Void>() {
            @Override
            public void onSuccess(E result) throws Exception {
                postGet(result);
            }
        });
        return scroller;
    }

    default List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException {
        List<E> all = getReadDao().getAll(pagingDto);
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
