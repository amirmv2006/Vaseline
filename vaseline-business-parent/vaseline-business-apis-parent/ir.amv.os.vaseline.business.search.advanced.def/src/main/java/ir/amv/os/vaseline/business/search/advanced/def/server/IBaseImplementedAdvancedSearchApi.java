package ir.amv.os.vaseline.business.search.advanced.def.server;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.search.advanced.api.server.IBaseAdvancedSearchApi;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.basic.def.server.action.function.IBusinessFunctionZero;
import ir.amv.os.vaseline.business.basic.def.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.ro.IBaseAdvancedSearchDao;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedAdvancedSearchApi<E extends IBaseEntity<Id>, SO extends IBaseSearchObject,
        Id extends Serializable, Dao extends IBaseAdvancedSearchDao<E, SO, Id>>
        extends IBaseAdvancedSearchApi<E, SO, Id>, IBaseImplementedReadOnlyApi<E, Id, Dao> {

    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default Long countBySearchObject(SO searchObject) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<Long>) () -> getDao().countBySearchObject(searchObject));
    }

    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> searchBySearchObject(SO searchObject) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<List<E>>) () -> {
            List<E> searchByExample = getDao().searchBySearchObject(searchObject);
            postGetList(searchByExample);
            return searchByExample;
        });
    }

    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default IVaselineDataScroller<E> scrollBySearchObject(SO searchObject, List<SortDto> sortList) throws
            BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<IVaselineDataScroller<E>>)() -> {
            IVaselineDataScroller<E> scroller = getDao().scrollBySearchObject(searchObject, sortList);
            scroller.addAfterFetchObject(this::postGet);
            return scroller;
        });
    }

    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> searchBySearchObject(SO searchObject, PagingDto pagingDto)
            throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<List<E>>) () -> {
            List<E> searchByExample = getDao().searchBySearchObject(searchObject, pagingDto);
            postGetList(searchByExample);
            return searchByExample;
        });
    }
}
