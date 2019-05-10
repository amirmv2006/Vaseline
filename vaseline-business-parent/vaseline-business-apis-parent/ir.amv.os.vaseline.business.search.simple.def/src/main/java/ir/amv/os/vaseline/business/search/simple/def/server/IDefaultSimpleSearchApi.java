package ir.amv.os.vaseline.business.search.simple.def.server;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.basic.def.server.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.business.search.simple.api.server.IBaseSimpleSearchApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.search.simple.api.server.ro.IBaseSimpleSearchDao;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface IDefaultSimpleSearchApi<I extends Serializable, E extends IBaseEntity<I>, D extends IBaseDto<I>,
        Dao extends IBaseSimpleSearchDao<I, E, D>>
        extends IBaseSimpleSearchApi<I, E, D>, IDefaultReadOnlyApi<I, E, Dao> {

    @Override
    @Transactional
    default Long countByExample(D example) throws BaseVaselineServerException {
        return getDao().countByExample(example);
    }

    @Override
    @Transactional
    default List<E> searchByExample(D example) throws BaseVaselineServerException {
        List<E> searchByExample = getDao().searchByExample(example);
        postGetList(searchByExample);
        return searchByExample;
    }

    @Override
    @Transactional
    default IVaselineDataScroller<E> scrollByExample(D example, List<SortDto> sortList) throws BaseVaselineServerException {
        IVaselineDataScroller<E> scroller = getDao().scrollByExample(example, sortList);
        scroller.addAfterFetchObject(this::postGet);
        return scroller;
    }

    @Override
    @Transactional
    default List<E> searchByExample(D example, PagingDto pagingDto)
            throws BaseVaselineServerException {
        List<E> searchByExample = getDao().searchByExample(example, pagingDto);
        postGetList(searchByExample);
        return searchByExample;
    }
}
