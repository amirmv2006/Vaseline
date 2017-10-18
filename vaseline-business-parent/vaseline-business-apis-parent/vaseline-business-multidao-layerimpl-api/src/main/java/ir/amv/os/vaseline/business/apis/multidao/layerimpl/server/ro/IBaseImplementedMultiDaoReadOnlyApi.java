package ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.notsupported.VaselineFeatureNotSupportedException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.business.apis.multidao.layer.server.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseImplementedMultiDaoReadOnlyApi<E extends IBaseEntity<Id>, Id extends Serializable, Category,
        Dao extends IBaseReadOnlyDao<E, Id>>
        extends IBaseMultiDaoReadOnlyApi<E, Id, Category>, IBaseImplementedReadOnlyApi<E, Id, Dao> {

    @Override
    default E getById(Id id) throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.getById(id);
    }
    default E getById(Category category, Id id) throws BaseVaselineServerException {
        E byId = getDaoFor(category).getById(id);
        postGet(byId);
        return byId;
    }

    @Override
    default Long countAll() throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.countAll();
    }
    default Long countAll(Category category) throws BaseVaselineServerException {
        return getDaoFor(category).countAll();
    }

    @Override
    default List<E> getAll() throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.getAll();
    }
    default List<E> getAll(Category category) throws BaseVaselineServerException {
        List<E> all = getDaoFor(category).getAll();
        postGetList(all);
        return all;
    }

    @Override
    default List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.getAll(pagingDto);
    }
    @Override
    default List<E> getAll(Category category, PagingDto pagingDto) throws BaseVaselineServerException {
        List<E> all = getDaoFor(category).getAll(pagingDto);
        postGetList(all);
        return all;
    }

    @Override
    default IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.scrollAll(sortList);
    }

    @Override
    default IVaselineDataScroller<E> scrollAll(Category category, List<SortDto> sortList) throws BaseVaselineServerException {
        IVaselineDataScroller<E> scroller = getDaoFor(category).scrollAll(sortList);
        scroller.addAfterFetchObject(new BaseCallbackImpl<E, Void>() {
            @Override
            public void onSuccess(E result) throws Exception {
                postGet(result);
            }
        });
        return scroller;
    }

    /**
     * @inheritDoc
     * @deprecated use {@link #getDaoFor(Object)}
     */
    @Override
    default Dao getDao() {
        throw new VaselineFeatureNotSupportedException();
    }
    Dao getDaoFor(Category category) throws BaseVaselineServerException;
}
