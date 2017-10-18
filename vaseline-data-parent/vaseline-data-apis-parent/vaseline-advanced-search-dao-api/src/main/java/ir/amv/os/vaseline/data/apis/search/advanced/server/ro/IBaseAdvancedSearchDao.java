package ir.amv.os.vaseline.data.apis.search.advanced.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 9/30/2017.
 */
public interface IBaseAdvancedSearchDao<E extends IBaseEntity<Id>, SO extends IBaseSearchObject, Id extends
        Serializable>
        extends IBaseReadOnlyDao<E, Id> {

    Long countBySearchObject(SO example);
    List<E> searchBySearchObject(SO example);
    List<E> searchBySearchObject(SO example, PagingDto pagingDto);
    IVaselineDataScroller<E> scrollBySearchObject(SO example, List<SortDto> sortList);
}
