package ir.amv.os.vaseline.business.multidao.def.server.advancedearch;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.multidao.def.server.ro.IDefaultMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.business.search.advanced.def.server.IDefaultAdvancedSearchApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.ro.IBaseAdvancedSearchDao;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface IDefaultMultiDaoAdvancedSearchApi<E extends IBaseEntity<Id>, SO extends IBaseSearchObject,
        Id extends Serializable, Category, Dao extends IBaseAdvancedSearchDao<E, SO, Id>>
        extends IDefaultMultiDaoReadOnlyApi<E, Id, Category, Dao>, IDefaultAdvancedSearchApi<E, SO, Id,
                Dao> {
    Category getCategoryForSearchObject(SO searchObject);

    @Override
    @Transactional
    default Long countBySearchObject(SO searchObject) throws BaseVaselineServerException {
        return getDaoFor(getCategoryForSearchObject(searchObject)).countBySearchObject(searchObject);
    }

    @Override
    @Transactional
    default List<E> searchBySearchObject(SO searchObject) throws BaseVaselineServerException {
        List<E> list = getDaoFor(getCategoryForSearchObject(searchObject)).searchBySearchObject(searchObject);
        postGetList(list);
        return list;
    }

    @Override
    @Transactional
    default List<E> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseVaselineServerException {
        List<E> list = getDaoFor(getCategoryForSearchObject(searchObject)).searchBySearchObject(searchObject, pagingDto);
        postGetList(list);
        return list;
    }

    @Override
    @Transactional
    default IVaselineDataScroller<E> scrollBySearchObject(SO searchObject, List<SortDto> sortList) throws
            BaseVaselineServerException {
        IVaselineDataScroller<E> scroller = getDaoFor(getCategoryForSearchObject(searchObject)).
                scrollBySearchObject(searchObject, sortList);
        scroller.addAfterFetchObject(this::postGet);
        return scroller;
    }
}
