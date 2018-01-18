package ir.amv.os.vaseline.ws.rest.apis.basic.layer.ro;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.common.apis.basic.layer.ro.IBaseReadOnlyWebService;
import ir.amv.os.vaseline.ws.rest.apis.basic.layer.base.IBaseRestService;

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
public interface IBaseReadOnlyRestService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyWebService<D, Id>, IBaseRestService {

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    D getById(@PathParam("id") Id id) throws BaseVaselineClientException;

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/count")
    Long countAll() throws BaseVaselineClientException;

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    List<D> getAll() throws BaseVaselineClientException;

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/page")
    List<D> getAll(PagingDto pagingDto) throws BaseVaselineClientException;

}
