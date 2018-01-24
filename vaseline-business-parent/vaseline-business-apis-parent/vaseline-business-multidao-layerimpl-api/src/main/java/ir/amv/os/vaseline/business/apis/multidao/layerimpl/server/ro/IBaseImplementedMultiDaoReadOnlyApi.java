package ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.notsupported.VaselineFeatureNotSupportedException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineDbOpMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionOneImpl;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionTwoImpl;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.business.apis.multidao.layer.server.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.Method;
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

    @Override
    @Transactional
    default E getById(Category category, Id id) throws BaseVaselineServerException {
        Method getByIdMethod = getDeclaredMethod(IBaseImplementedMultiDaoReadOnlyApi.class, "getById",
                Object.class, Serializable.class);
        return doBusinessAction(new BusinessFunctionTwoImpl<>(
                getClass(), getByIdMethod, category, id, (c, i) -> {
            E byId = getDaoFor(c).getById(i);
            postGet(byId);
            return byId;
        }, VaselineDbOpMetadata.READ_ONLY
        ));

    }

    @Override
    default Long countAll() throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.countAll();
    }
    @Override
    @Transactional
    default Long countAll(Category category) throws BaseVaselineServerException {
        Method countAllMethod = getDeclaredMethod(IBaseImplementedMultiDaoReadOnlyApi.class, "countAll", Object.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                getClass(), countAllMethod, category, c -> getDaoFor(c).countAll(), VaselineDbOpMetadata.READ_ONLY
        ));
    }

    @Override
    default List<E> getAll() throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.getAll();
    }
    @Override
    @Transactional
    default List<E> getAll(Category category) throws BaseVaselineServerException {
        Method getAllMethod = getDeclaredMethod(IBaseImplementedMultiDaoReadOnlyApi.class, "getAll", Object.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                getClass(), getAllMethod, category, c -> {
            List<E> all = getDaoFor(c).getAll();
            postGetList(all);
            return all;
        }, VaselineDbOpMetadata.READ_ONLY
        ));
    }

    @Override
    default List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.getAll(pagingDto);
    }
    @Override
    @Transactional
    default List<E> getAll(Category category, PagingDto pagingDto) throws BaseVaselineServerException {
        Method getAllMethod = getDeclaredMethod(IBaseImplementedMultiDaoReadOnlyApi.class, "getAll", Object.class,
                PagingDto.class);
        return doBusinessAction(new BusinessFunctionTwoImpl<>(
                getClass(), getAllMethod, category, pagingDto, (c, p) -> {
            List<E> all = getDaoFor(c).getAll(p);
            postGetList(all);
            return all;
        }, VaselineDbOpMetadata.READ_ONLY
        ));
    }

    @Override
    default IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.scrollAll(sortList);
    }

    @Override
    @Transactional
    default IVaselineDataScroller<E> scrollAll(Category category, List<SortDto> sortList) throws BaseVaselineServerException {
        Method scrollAllMethod = getDeclaredMethod(IBaseImplementedMultiDaoReadOnlyApi.class, "scrollAll", Object
                .class, List.class);
        return doBusinessAction(new BusinessFunctionTwoImpl<>(
                getClass(), scrollAllMethod, category, sortList, (c, sl) -> {
            IVaselineDataScroller<E> scroller = getDaoFor(c).scrollAll(sl);
            scroller.addAfterFetchObject(this::postGet);
            return scroller;
        }, VaselineDbOpMetadata.READ_ONLY
        ));
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
