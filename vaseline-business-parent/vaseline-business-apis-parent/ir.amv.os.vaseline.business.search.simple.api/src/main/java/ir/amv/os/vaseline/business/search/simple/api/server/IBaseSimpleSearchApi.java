package ir.amv.os.vaseline.business.search.simple.api.server;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.basic.api.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;

import java.io.Serializable;
import java.util.List;

public interface IBaseSimpleSearchApi<I extends Serializable, E extends IBaseEntity<I>, D extends IBaseDto<I>>
        extends IBaseReadOnlyApi<I, E> {

    Long countByExample(D example) throws BaseVaselineServerException;
    List<E> searchByExample(D example) throws BaseVaselineServerException;
    IVaselineDataScroller<E> scrollByExample(D example, List<SortDto> sortList) throws BaseVaselineServerException;
    List<E> searchByExample(D example, PagingDto pagingDto)
            throws BaseVaselineServerException;
}
