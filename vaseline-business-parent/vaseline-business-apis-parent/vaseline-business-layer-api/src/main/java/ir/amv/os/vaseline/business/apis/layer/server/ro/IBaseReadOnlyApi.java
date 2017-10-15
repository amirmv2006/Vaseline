package ir.amv.os.vaseline.business.apis.layer.server.ro;

import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.business.apis.layer.server.ro.IBaseEntityReadOnlyApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;

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
    IVaselineDataScroller<E> scrollAll() throws BaseVaselineServerException;
    List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException;

}
