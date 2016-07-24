package ir.amv.os.vaseline.security.authorization.api.shared.base.api.ro;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.api.IBaseReadOnlyApi;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/25/2016.
 */
public interface IBaseSecuredReadOnlyApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyApi<E, D, Id> {

    String BASE_OP_READ = "read";

    E getByIdNotSecured(Id id) throws BaseVaselineServerException;

    Long countAllNotSecured() throws BaseVaselineServerException;
    List<E> getAllNotSecured() throws BaseVaselineServerException;
    IVaselineDataScroller<E> scrollAllNotSecured() throws BaseVaselineServerException;
    List<E> getAllNotSecured(PagingDto pagingDto) throws BaseVaselineServerException;

    Long countByExampleNotSecured(D example) throws BaseVaselineServerException;
    List<E> searchByExampleNotSecured(D example) throws BaseVaselineServerException;
    IVaselineDataScroller scrollByExampleNotSecured(D example) throws BaseVaselineServerException;
    List<E> searchByExampleNotSecured(D example, PagingDto pagingDto)
            throws BaseVaselineServerException;

    String getReadOperationTreeName();
    String getRootOperationTreeName();
}
