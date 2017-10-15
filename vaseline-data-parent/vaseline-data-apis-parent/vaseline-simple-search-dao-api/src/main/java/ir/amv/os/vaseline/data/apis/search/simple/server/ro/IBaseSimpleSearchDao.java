package ir.amv.os.vaseline.data.apis.search.simple.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.apis.dao.server.base.IBaseDao;
import ir.amv.os.vaseline.data.apis.dao.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 9/30/2017.
 */
public interface IBaseSimpleSearchDao<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyDao<E, Id> {

    Long countByExample(D example);
    List<E> searchByExample(D example);
    List<E> searchByExample(D example, PagingDto pagingDto);
    IVaselineDataScroller<E> scrollByExample(D example, List<SortDto> sortList);

}
