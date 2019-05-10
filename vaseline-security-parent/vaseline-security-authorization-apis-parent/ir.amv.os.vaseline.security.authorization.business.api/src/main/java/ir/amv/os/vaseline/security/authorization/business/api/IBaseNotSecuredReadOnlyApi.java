package ir.amv.os.vaseline.security.authorization.business.api;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.basic.api.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.annot.NoAuthorization;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseNotSecuredReadOnlyApi<E extends IBaseEntity<Id>, Id extends Serializable>
        extends IBaseReadOnlyApi<Id, E> {

    @NoAuthorization
    E getByIdNotSecured(Id id) throws BaseVaselineServerException;

    @NoAuthorization
    Long countAllApproximatelyNotSecured() throws BaseVaselineServerException;

    @NoAuthorization
    Long countAllNotSecured() throws BaseVaselineServerException;
    @NoAuthorization
    List<E> getAllNotSecured() throws BaseVaselineServerException;
    @NoAuthorization
    IVaselineDataScroller<E> scrollAllNotSecured(final List<SortDto> sortList) throws BaseVaselineServerException;
    @NoAuthorization
    List<E> getAllNotSecured(PagingDto pagingDto) throws BaseVaselineServerException;
}
