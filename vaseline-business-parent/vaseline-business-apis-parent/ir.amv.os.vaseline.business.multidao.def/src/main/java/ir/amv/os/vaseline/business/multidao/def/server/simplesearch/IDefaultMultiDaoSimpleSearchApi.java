package ir.amv.os.vaseline.business.multidao.def.server.simplesearch;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.business.multidao.def.server.ro.IDefaultMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.business.search.simple.def.server.IDefaultSimpleSearchApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.search.simple.api.server.ro.IBaseSimpleSearchDao;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface IDefaultMultiDaoSimpleSearchApi<Category, Id extends
        Serializable, E extends IBaseBusinessModel<Id>, D extends IBaseDto<Id>, Dao extends IBaseSimpleSearchDao<Id, E, D>>
        extends IDefaultMultiDaoReadOnlyApi<Category, Id, E, Dao>, IDefaultSimpleSearchApi<Id, E, D,
        Dao> {

    Category getCategoryForDto(D dto);

    @Override
    @Transactional
    default Long countByExample(D example) throws BaseBusinessException {
        return getDaoFor(getCategoryForDto(example)).countByExample(example);
    }

    @Override
    @Transactional
    default List<E> searchByExample(D example) throws BaseBusinessException {
        List<E> list = getDaoFor(getCategoryForDto(example)).searchByExample(example);
        postGetList(list);
        return list;
    }

    @Override
    @Transactional
    default List<E> searchByExample(D example, PagingDto pagingDto) throws BaseBusinessException {
        List<E> list = getDaoFor(getCategoryForDto(example)).searchByExample(example, pagingDto);
        postGetList(list);
        return list;
    }

    @Override
    @Transactional
    default IVaselineDataScroller<E> scrollByExample(D example, List<SortDto> sortList) throws BaseBusinessException {
        IVaselineDataScroller<E> scroller = getDaoFor(getCategoryForDto(example)).scrollByExample(example, sortList);
        scroller.addAfterFetchObject(this::postGet);
        return scroller;
    }
}
