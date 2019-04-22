package ir.amv.os.vaseline.ws.rest.search.advanced.api;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
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
public interface IBaseAdvancedSearchRestService<D extends IBaseDto<Id>, SO extends IBaseSearchObject, Id extends Serializable>
        extends IBaseAdvancedSearchWebService<D, SO, Id>, IBaseReadOnlyRestService<D, Id> {

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/query/count")
    Long countBySearchObject(SO searchObject) throws BaseVaselineClientException;
    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/query/all")
    List<D> searchBySearchObject(SO searchObject) throws BaseVaselineClientException;
    @Override
    List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseVaselineClientException;
}
