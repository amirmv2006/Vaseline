package ir.amv.os.vaseline.data.search.simple.api.server.ro;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 9/30/2017.
 */
public interface IBaseSimpleSearchDao<I extends Serializable, E extends IBaseEntity<I>, D extends IBaseDto<I>>
        extends IBaseReadOnlyDao<I, E> {

    Long countByExample(D example);
    List<E> searchByExample(D example);
    List<E> searchByExample(D example, PagingDto pagingDto);
    IVaselineDataScroller<E> scrollByExample(D example, List<SortDto> sortList);

}
