package ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.notsupported.VaselineFeatureNotSupportedException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function.IBusinessFunctionZero;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.business.apis.multidao.layer.server.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;

import javax.transaction.Transactional;
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

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default E getById(Category category, Id id) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<E>) () -> {
            E byId = getDaoFor(category).getById(id);
            postGet(byId);
            return byId;
        });

    }

    @Override
    default Long countAll() throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.countAll();
    }
    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default Long countAll(Category category) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<Long>) () -> getDaoFor(category).countAll());
    }

    @Override
    default List<E> getAll() throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.getAll();
    }
    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> getAll(Category category) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<List<E>>) () -> {
            List<E> all = getDaoFor(category).getAll();
            postGetList(all);
            return all;
        });
    }

    @Override
    default List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.getAll(pagingDto);
    }
    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> getAll(Category category, PagingDto pagingDto) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<List<E>>) () -> {
            List<E> all = getDaoFor(category).getAll(pagingDto);
            postGetList(all);
            return all;
        });
    }

    @Override
    default IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.scrollAll(sortList);
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default IVaselineDataScroller<E> scrollAll(Category category, List<SortDto> sortList) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<IVaselineDataScroller<E>>) () -> {
            IVaselineDataScroller<E> scroller = getDaoFor(category).scrollAll(sortList);
            scroller.addAfterFetchObject(this::postGet);
            return scroller;
        });
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
