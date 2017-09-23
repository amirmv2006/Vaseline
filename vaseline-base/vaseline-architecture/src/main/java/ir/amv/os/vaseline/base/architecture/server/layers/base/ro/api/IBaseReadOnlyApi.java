package ir.amv.os.vaseline.base.architecture.server.layers.base.ro.api;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.base.architecture.server.layers.ent.ro.api.IBaseEntityReadOnlyApi;
import ir.amv.os.vaseline.base.core.api.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.paging.PagingDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseReadOnlyApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseEntityReadOnlyApi<E> {

    E getById(Id id) throws BaseVaselineServerException;

    Long countAllApproximately() throws BaseVaselineServerException;

    Long countAll() throws BaseVaselineServerException;
    List<E> getAll() throws BaseVaselineServerException;
    IVaselineDataScroller<E> scrollAll() throws BaseVaselineServerException;
    List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException;

    Long countByExample(D example) throws BaseVaselineServerException;
    List<E> searchByExample(D example) throws BaseVaselineServerException;
    IVaselineDataScroller<E> scrollByExample(D example) throws BaseVaselineServerException;
    List<E> searchByExample(D example, PagingDto pagingDto)
            throws BaseVaselineServerException;

}
