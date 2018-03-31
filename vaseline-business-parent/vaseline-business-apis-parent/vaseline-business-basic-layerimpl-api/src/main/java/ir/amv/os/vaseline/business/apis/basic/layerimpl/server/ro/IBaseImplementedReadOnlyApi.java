package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function.IBusinessFunctionZero;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedReadOnlyApi<E extends IBaseEntity<Id>, Id extends Serializable, Dao extends
        IBaseReadOnlyDao<E, Id>>
        extends IBaseReadOnlyApi<E, Id>, IBaseImplementedEntityReadOnlyApi<E> {

    Dao getDao();

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default E getById(Id id) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<E>) () -> {
            E findById = getDao().getById(id);
            postGet(findById);
            return findById;
        });
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default Long countAllApproximately() throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<Long>) () -> getDao().countAllApproximately());
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default Long countAll() throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<Long>) () -> getDao().countAll());
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> getAll() throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<List<E>>) () -> {
            List<E> all = getDao().getAll();
            postGetList(all);
            return all;
        });
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<IVaselineDataScroller<E>>) () -> {
            IVaselineDataScroller<E> scroller = getDao().scrollAll(sortList);
            scroller.addAfterFetchObject(this::postGet);
            return scroller;
        });
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<List<E>>) () -> {
            List<E> all = getDao().getAll(pagingDto);
            postGetList(all);
            return all;
        });
    }

    default void postGetList(final List<E> list) throws BaseVaselineServerException {
        if (list != null) {
            for (E entity : list) {
                postGet(entity);
            }
        }
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
