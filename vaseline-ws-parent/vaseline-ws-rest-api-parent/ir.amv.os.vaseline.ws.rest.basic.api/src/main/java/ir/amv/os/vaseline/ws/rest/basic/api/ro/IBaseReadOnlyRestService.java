package ir.amv.os.vaseline.ws.rest.basic.api.ro;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.ws.common.basic.api.ro.IBaseReadOnlyWebService;
import ir.amv.os.vaseline.ws.rest.basic.api.base.IBaseRestService;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Amir
 */
public interface IBaseReadOnlyRestService<Id extends Serializable, D extends IBaseDto<Id>>
        extends IBaseReadOnlyWebService<Id, D>, IBaseRestService {

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    D getById(@PathParam("id") Id id) throws BaseExternalException;

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/count")
    Long countAll() throws BaseExternalException;

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    List<D> getAll() throws BaseExternalException;

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/page")
    List<D> getAll(PagingDto pagingDto) throws BaseExternalException;

}
