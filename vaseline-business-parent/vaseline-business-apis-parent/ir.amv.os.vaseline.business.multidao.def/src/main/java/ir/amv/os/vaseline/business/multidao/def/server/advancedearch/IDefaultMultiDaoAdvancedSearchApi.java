package ir.amv.os.vaseline.business.multidao.def.server.advancedearch;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.business.multidao.def.server.ro.IDefaultMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.business.search.advanced.def.server.IDefaultAdvancedSearchApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.ro.IBaseAdvancedSearchRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface IDefaultMultiDaoAdvancedSearchApi<Category, Id extends Serializable, E extends IBaseBusinessModel<Id>, SO extends IBaseSearchObject,
        Dao extends IBaseAdvancedSearchRepository<Id, E, SO>>
        extends IDefaultMultiDaoReadOnlyApi<Category, Id, E, Dao>, IDefaultAdvancedSearchApi<Id, E, SO,
        Dao> {
    Category getCategoryForSearchObject(SO searchObject);

    @Override
    @Transactional
    default Long countBySearchObject(SO searchObject) throws BaseBusinessException {
        return getDaoFor(getCategoryForSearchObject(searchObject)).countBySearchObject(searchObject);
    }

    @Override
    @Transactional
    default List<E> searchBySearchObject(SO searchObject) throws BaseBusinessException {
        List<E> list = getDaoFor(getCategoryForSearchObject(searchObject)).searchBySearchObject(searchObject);
        postGetList(list);
        return list;
    }

    @Override
    @Transactional
    default List<E> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseBusinessException {
        List<E> list = getDaoFor(getCategoryForSearchObject(searchObject)).searchBySearchObject(searchObject, pagingDto);
        postGetList(list);
        return list;
    }

    @Override
    @Transactional
    default IVaselineDataScroller<E> scrollBySearchObject(SO searchObject, List<SortDto> sortList) throws
            BaseBusinessException {
        IVaselineDataScroller<E> scroller = getDaoFor(getCategoryForSearchObject(searchObject)).
                scrollBySearchObject(searchObject, sortList);
        scroller.addAfterFetchObject(this::postGet);
        return scroller;
    }
}
