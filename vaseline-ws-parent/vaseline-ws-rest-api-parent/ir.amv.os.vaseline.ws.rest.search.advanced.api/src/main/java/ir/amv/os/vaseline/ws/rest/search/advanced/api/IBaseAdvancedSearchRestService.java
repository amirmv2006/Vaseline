package ir.amv.os.vaseline.ws.rest.search.advanced.api;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.ws.common.search.advanced.api.IBaseAdvancedSearchWebService;
import ir.amv.os.vaseline.ws.rest.basic.api.ro.IBaseReadOnlyRestService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseAdvancedSearchRestService<Id extends Serializable, D extends IBaseDto<Id>, SO extends IBaseSearchObject>
        extends IBaseAdvancedSearchWebService<Id, D, SO>, IBaseReadOnlyRestService<Id, D> {

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/query/count")
    Long countBySearchObject(SO searchObject) throws BaseExternalException;
    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/query/all")
    List<D> searchBySearchObject(SO searchObject) throws BaseExternalException;
    @Override
    List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseExternalException;
}
