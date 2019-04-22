package ir.amv.os.vaseline.business.multidao.def.server.simplesearch;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.basic.def.server.action.function.IBusinessFunctionNoArg;
import ir.amv.os.vaseline.business.multidao.def.server.ro.IDefaultMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.business.search.simple.def.server.IDefaultSimpleSearchApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.search.simple.api.server.ro.IBaseSimpleSearchDao;

import java.io.Serializable;
import java.util.List;

public interface IDefaultMultiDaoSimpleSearchApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends
        Serializable, Category, Dao extends IBaseSimpleSearchDao<E, D, Id>>
        extends IDefaultMultiDaoReadOnlyApi<E, Id, Category, Dao>, IDefaultSimpleSearchApi<E, D, Id,
                Dao> {

    Category getCategoryForDto(D dto);

    @Override
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default Long countByExample(D example) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionNoArg<Long>) () -> getDaoFor(getCategoryForDto(example)).countByExample
                (example));
    }

    @Override
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> searchByExample(D example) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionNoArg<List<E>>) () -> {
            List<E> list = getDaoFor(getCategoryForDto(example)).searchByExample(example);
            postGetList(list);
            return list;
        });
    }

    @Override
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionNoArg<List<E>>) () -> {
            List<E> list = getDaoFor(getCategoryForDto(example)).searchByExample(example, pagingDto);
            postGetList(list);
            return list;
        });
    }

    @Override
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default IVaselineDataScroller<E> scrollByExample(D example, List<SortDto> sortList) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionNoArg<IVaselineDataScroller<E>>) () -> {
            IVaselineDataScroller<E> scroller = getDaoFor(getCategoryForDto(example)).scrollByExample(example, sortList);
            scroller.addAfterFetchObject(this::postGet);
            return scroller;
        });
    }
}
