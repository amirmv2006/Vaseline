package ir.amv.os.vaseline.security.authorization.business.api;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.annot.NoAuthorization;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseNotSecuredReadOnlyApi<E extends IBaseBusinessModel<Id>, Id extends Serializable>
        extends IBaseReadOnlyApi<Id, E> {

    @NoAuthorization
    E getByIdNotSecured(Id id) throws BaseBusinessException;

    @NoAuthorization
    Long countAllApproximatelyNotSecured() throws BaseBusinessException;

    @NoAuthorization
    Long countAllNotSecured() throws BaseBusinessException;
    @NoAuthorization
    List<E> getAllNotSecured() throws BaseBusinessException;
    @NoAuthorization
    IVaselineDataScroller<E> scrollAllNotSecured(final List<SortDto> sortList) throws BaseBusinessException;
    @NoAuthorization
    List<E> getAllNotSecured(PagingDto pagingDto) throws BaseBusinessException;
}
