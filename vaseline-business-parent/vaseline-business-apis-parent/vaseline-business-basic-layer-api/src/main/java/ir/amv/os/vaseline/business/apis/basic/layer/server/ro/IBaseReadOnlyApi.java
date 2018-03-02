package ir.amv.os.vaseline.business.apis.basic.layer.server.ro;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseReadOnlyApi<E extends IBaseEntity<Id>, Id extends Serializable>
        extends IBaseEntityReadOnlyApi<E> {

    E getById(Id id) throws BaseVaselineServerException;

    Long countAllApproximately() throws BaseVaselineServerException;

    Long countAll() throws BaseVaselineServerException;
    List<E> getAll() throws BaseVaselineServerException;
    IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseVaselineServerException;
    List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException;

}
