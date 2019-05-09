package ir.amv.os.vaseline.data.search.advanced.api.server.ro;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 9/30/2017.
 */
public interface IBaseAdvancedSearchDao<I extends Serializable, E extends IBaseEntity<I>, SO extends IBaseSearchObject>
        extends IBaseReadOnlyDao<I, E> {

    Long countBySearchObject(SO example);
    List<E> searchBySearchObject(SO example);
    List<E> searchBySearchObject(SO example, PagingDto pagingDto);
    IVaselineDataScroller<E> scrollBySearchObject(SO example, List<SortDto> sortList);
}
