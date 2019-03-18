package ir.amv.os.vaseline.business.search.simple.def.server;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.basic.def.server.action.function.IBusinessFunctionZero;
import ir.amv.os.vaseline.business.basic.def.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.business.search.simple.api.server.IBaseSimpleSearchApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.search.simple.api.server.ro.IBaseSimpleSearchDao;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedSimpleSearchApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>,
        Id extends Serializable, Dao extends IBaseSimpleSearchDao<E, D, Id>>
        extends IBaseSimpleSearchApi<E, D, Id>, IBaseImplementedReadOnlyApi<E, Id, Dao> {

    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default Long countByExample(D example) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<Long>) () -> getDao().countByExample(example));
    }

    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> searchByExample(D example) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<List<E>>) () -> {
            List<E> searchByExample = getDao().searchByExample(example);
            postGetList(searchByExample);
            return searchByExample;
        });
    }

    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default IVaselineDataScroller<E> scrollByExample(D example, List<SortDto> sortList) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<IVaselineDataScroller<E>>) () -> {
            IVaselineDataScroller<E> scroller = getDao().scrollByExample(example, sortList);
            scroller.addAfterFetchObject(this::postGet);
            return scroller;
        });
    }

    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> searchByExample(D example, PagingDto pagingDto)
            throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<List<E>>) () -> {
            List<E> searchByExample = getDao().searchByExample(example, pagingDto);
            postGetList(searchByExample);
            return searchByExample;
        });
    }
}
