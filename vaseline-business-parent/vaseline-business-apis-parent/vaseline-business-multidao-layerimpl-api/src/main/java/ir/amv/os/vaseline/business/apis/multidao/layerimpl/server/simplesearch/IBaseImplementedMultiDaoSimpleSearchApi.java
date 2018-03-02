package ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.simplesearch;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function.IBusinessFunctionZero;
import ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.ro.IBaseImplementedMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.business.apis.simplesearch.layerimpl.server.IBaseImplementedSimpleSearchApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.simple.server.ro.IBaseSimpleSearchDao;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedMultiDaoSimpleSearchApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends
        Serializable, Category, Dao extends IBaseSimpleSearchDao<E, D, Id>>
        extends IBaseImplementedMultiDaoReadOnlyApi<E, Id, Category, Dao>, IBaseImplementedSimpleSearchApi<E, D, Id,
        Dao> {

    Category getCategoryForDto(D dto);

    @Override
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default Long countByExample(D example) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<Long>) () -> getDaoFor(getCategoryForDto(example)).countByExample
                (example));
    }

    @Override
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> searchByExample(D example) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<List<E>>) () -> {
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
        return doBusinessAction((IBusinessFunctionZero<List<E>>) () -> {
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
        return doBusinessAction((IBusinessFunctionZero<IVaselineDataScroller<E>>) () -> {
            IVaselineDataScroller<E> scroller = getDaoFor(getCategoryForDto(example)).scrollByExample(example, sortList);
            scroller.addAfterFetchObject(this::postGet);
            return scroller;
        });
    }
}
