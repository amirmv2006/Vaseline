package ir.amv.os.vaseline.security.authorization.business.def;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.basic.def.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.security.authorization.business.api.IBaseNotSecuredReadOnlyApi;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseImplementedNotSecuredReadOnlyApi<E extends IBaseEntity<Id>, Id extends Serializable, Dao extends
        IBaseReadOnlyDao<E, Id>>
        extends IBaseImplementedReadOnlyApi<E, Id, Dao>,
        IBaseNotSecuredReadOnlyApi<E, Id> {

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default E getByIdNotSecured(Id id) throws BaseVaselineServerException {
        return IBaseImplementedReadOnlyApi.super.getById(id);
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default Long countAllApproximatelyNotSecured() throws BaseVaselineServerException {
        return IBaseImplementedReadOnlyApi.super.countAllApproximately();
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default Long countAllNotSecured() throws BaseVaselineServerException {
        return IBaseImplementedReadOnlyApi.super.countAll();
    }
    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> getAllNotSecured() throws BaseVaselineServerException {
        return IBaseImplementedReadOnlyApi.super.getAll();
    }
    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default IVaselineDataScroller<E> scrollAllNotSecured(final List<SortDto> sortList) throws
            BaseVaselineServerException {
        return IBaseImplementedReadOnlyApi.super.scrollAll(sortList);
    }
    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> getAllNotSecured(PagingDto pagingDto) throws BaseVaselineServerException {
        return IBaseImplementedReadOnlyApi.super.getAll(pagingDto);
    }
}
