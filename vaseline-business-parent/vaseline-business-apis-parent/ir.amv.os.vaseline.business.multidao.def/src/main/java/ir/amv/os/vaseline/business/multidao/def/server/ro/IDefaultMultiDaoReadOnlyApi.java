package ir.amv.os.vaseline.business.multidao.def.server.ro;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.notsupported.VaselineFeatureNotSupportedException;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.business.basic.def.server.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.business.multidao.api.server.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IDefaultMultiDaoReadOnlyApi<Category, Id extends Serializable, E extends IBaseBusinessModel<Id>,
        Dao extends IBaseReadOnlyDao<Id, E>>
        extends IBaseMultiDaoReadOnlyApi<Category, Id, E>, IDefaultReadOnlyApi<Id, E, Dao> {

    @Override
    default E getById(Id id) throws BaseBusinessException {
        return IBaseMultiDaoReadOnlyApi.super.getById(id);
    }

    @Override
    @Transactional
    default E getById(Category category, Id id) throws BaseBusinessException {
        E byId = getDaoFor(category).getById(id);
        postGet(byId);
        return byId;
    }

    @Override
    default Long countAll() throws BaseBusinessException {
        return IBaseMultiDaoReadOnlyApi.super.countAll();
    }
    @Override
    @Transactional
    default Long countAll(Category category) throws BaseBusinessException {
        return getDaoFor(category).countAll();
    }

    @Override
    default List<E> getAll() throws BaseBusinessException {
        return IBaseMultiDaoReadOnlyApi.super.getAll();
    }
    @Override
    @Transactional
    default List<E> getAll(Category category) throws BaseBusinessException {
        List<E> all = getDaoFor(category).getAll();
        postGetList(all);
        return all;
    }

    @Override
    default List<E> getAll(PagingDto pagingDto) throws BaseBusinessException {
        return IBaseMultiDaoReadOnlyApi.super.getAll(pagingDto);
    }
    @Override
    @Transactional
    default List<E> getAll(Category category, PagingDto pagingDto) throws BaseBusinessException {
        List<E> all = getDaoFor(category).getAll(pagingDto);
        postGetList(all);
        return all;
    }

    @Override
    default IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseBusinessException {
        return IBaseMultiDaoReadOnlyApi.super.scrollAll(sortList);
    }

    @Override
    @Transactional
    default IVaselineDataScroller<E> scrollAll(Category category, List<SortDto> sortList) throws BaseBusinessException {
        IVaselineDataScroller<E> scroller = getDaoFor(category).scrollAll(sortList);
        scroller.addAfterFetchObject(this::postGet);
        return scroller;
    }

    /**
     * {@inheritDoc}
     * @deprecated use {@link #getDaoFor(Object)}
     */
    @Override
    default Dao getDao() {
        throw new VaselineFeatureNotSupportedException();
    }
    Dao getDaoFor(Category category) throws BaseBusinessException;
}
