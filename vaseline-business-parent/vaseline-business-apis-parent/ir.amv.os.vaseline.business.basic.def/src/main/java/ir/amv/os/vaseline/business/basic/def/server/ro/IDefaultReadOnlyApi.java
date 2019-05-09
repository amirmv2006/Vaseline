package ir.amv.os.vaseline.business.basic.def.server.ro;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.basic.api.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface IDefaultReadOnlyApi<E extends IBaseEntity<Id>, Id extends Serializable, Dao extends
        IBaseReadOnlyDao<Id, E>>
        extends IBaseReadOnlyApi<E, Id>, IDefaultEntityReadOnlyApi<E> {

    Dao getDao();

    @Override
    @Transactional
    default E getById(Id id) throws BaseVaselineServerException {
        E findById = getDao().getById(id);
        postGet(findById);
        return findById;
    }

    @Override
    @Transactional
    default Long countAllApproximately() throws BaseVaselineServerException {
        return getDao().countAllApproximately();
    }

    @Override
    @Transactional
    default Long countAll() throws BaseVaselineServerException {
        return getDao().countAll();
    }

    @Override
    @Transactional
    default List<E> getAll() throws BaseVaselineServerException {
        List<E> all = getDao().getAll();
        postGetList(all);
        return all;
    }

    @Override
    @Transactional
    default IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseVaselineServerException {
        IVaselineDataScroller<E> scroller = getDao().scrollAll(sortList);
        scroller.addAfterFetchObject(this::postGet);
        return scroller;
    }

    @Override
    @Transactional
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

    @Override
    default Class<? extends E> getEntityClass() {
        return getDao().getEntityClass();
    }

    @Override
    default E newEntity() throws BaseVaselineServerException {
        try {
            return getDao().newEntity();
        } catch (Exception e) {
            throw new BaseVaselineServerException("Can not instantiate Entity", e);
        }
    }
}
