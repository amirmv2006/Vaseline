package ir.amv.os.vaseline.business.basic.api.server.ro;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseReadOnlyApi<I extends Serializable, E extends IBaseEntity<I>>
        extends IBaseEntityReadOnlyApi<E> {

    E getById(I id) throws BaseVaselineServerException;

    Long countAllApproximately() throws BaseVaselineServerException;

    Long countAll() throws BaseVaselineServerException;
    List<E> getAll() throws BaseVaselineServerException;
    IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseVaselineServerException;
    List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException;

}
