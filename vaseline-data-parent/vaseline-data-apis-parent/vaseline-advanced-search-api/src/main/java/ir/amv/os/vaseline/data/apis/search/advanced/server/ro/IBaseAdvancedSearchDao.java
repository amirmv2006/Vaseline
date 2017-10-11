package ir.amv.os.vaseline.data.apis.search.advanced.server.ro;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.apis.dao.server.base.IBaseDao;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;

import java.util.List;

/**
 * Created by AMV on 9/30/2017.
 */
public interface IBaseAdvancedSearchDao<E, SO extends IBaseSearchObject>
        extends IBaseDao{

    Long countBySearchObject(SO example);
    List<E> searchBySearchObject(SO example);
    List<E> searchBySearchObject(SO example, PagingDto pagingDto);
    IVaselineDataScroller<E> scrollBySearchObject(SO example, List<SortDto> sortList);
}
