package ir.amv.os.vaseline.business.basic.def.server.ro;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface IDefaultReadOnlyApi<I extends Serializable, E extends IBaseBusinessModel<I>, Dao extends
        IBaseReadOnlyDao<I, E>>
        extends IBaseReadOnlyApi<I, E>, IDefaultEntityReadOnlyApi<E> {

    Dao getDao();

    @Override
    @Transactional
    default E getById(I id) throws BaseBusinessException {
        E findById = getDao().getById(id);
        postGet(findById);
        return findById;
    }

    @Override
    @Transactional
    default Long countAllApproximately() throws BaseBusinessException {
        return getDao().countAllApproximately();
    }

    @Override
    @Transactional
    default Long countAll() throws BaseBusinessException {
        return getDao().countAll();
    }

    @Override
    @Transactional
    default List<E> getAll() throws BaseBusinessException {
        List<E> all = getDao().getAll();
        postGetList(all);
        return all;
    }

    @Override
    @Transactional
    default IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseBusinessException {
        IVaselineDataScroller<E> scroller = getDao().scrollAll(sortList);
        scroller.addAfterFetchObject(this::postGet);
        return scroller;
    }

    @Override
    @Transactional
    default List<E> getAll(PagingDto pagingDto) throws BaseBusinessException {
        List<E> all = getDao().getAll(pagingDto);
        postGetList(all);
        return all;
    }

    default void postGetList(final List<E> list) throws BaseBusinessException {
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
    default E newEntity() throws BaseBusinessException {
        try {
            return getDao().newEntity();
        } catch (Exception e) {
            throw new BaseBusinessException("Can not instantiate Entity", e);
        }
    }
}
