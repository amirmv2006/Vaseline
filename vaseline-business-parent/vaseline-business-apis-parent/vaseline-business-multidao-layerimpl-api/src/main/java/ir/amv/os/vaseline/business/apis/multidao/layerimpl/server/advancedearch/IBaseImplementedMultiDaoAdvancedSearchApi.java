package ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.advancedearch;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.apis.advancedsearch.layerimpl.server.IBaseImplementedAdvancedSearchApi;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function.IBusinessFunctionZero;
import ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.ro.IBaseImplementedMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.apis.search.advanced.server.ro.IBaseAdvancedSearchDao;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedMultiDaoAdvancedSearchApi<E extends IBaseEntity<Id>, SO extends IBaseSearchObject,
        Id extends Serializable, Category, Dao extends IBaseAdvancedSearchDao<E, SO, Id>>
        extends IBaseImplementedMultiDaoReadOnlyApi<E, Id, Category, Dao>, IBaseImplementedAdvancedSearchApi<E, SO, Id,
        Dao> {
    Category getCategoryForSearchObject(SO searchObject);

    @Override
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default Long countBySearchObject(SO searchObject) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<Long>) () -> getDaoFor(getCategoryForSearchObject(searchObject))
                .countBySearchObject(searchObject)
        );
    }

    @Override
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> searchBySearchObject(SO searchObject) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<List<E>>) () -> {
            List<E> list = getDaoFor(getCategoryForSearchObject(searchObject)).searchBySearchObject(searchObject);
            postGetList(list);
            return list;
        });
    }

    @Override
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default List<E> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<List<E>>) () -> {
            List<E> list = getDaoFor(getCategoryForSearchObject(searchObject)).searchBySearchObject(searchObject, pagingDto);
            postGetList(list);
            return list;
        });
    }

    @Override
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    default IVaselineDataScroller<E> scrollBySearchObject(SO searchObject, List<SortDto> sortList) throws
            BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<IVaselineDataScroller<E>>) () -> {
            IVaselineDataScroller<E> scroller = getDaoFor(getCategoryForSearchObject(searchObject)).
                    scrollBySearchObject(searchObject, sortList);
            scroller.addAfterFetchObject(this::postGet);
            return scroller;
        });
    }
}
