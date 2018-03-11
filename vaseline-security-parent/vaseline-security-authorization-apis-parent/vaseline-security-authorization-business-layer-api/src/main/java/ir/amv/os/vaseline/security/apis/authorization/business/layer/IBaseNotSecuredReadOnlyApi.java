package ir.amv.os.vaseline.security.apis.authorization.business.layer;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.apis.basic.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.annot.NoAuthorization;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseNotSecuredReadOnlyApi<E extends IBaseEntity<Id>, Id extends Serializable>
        extends IBaseReadOnlyApi<E, Id> {

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
