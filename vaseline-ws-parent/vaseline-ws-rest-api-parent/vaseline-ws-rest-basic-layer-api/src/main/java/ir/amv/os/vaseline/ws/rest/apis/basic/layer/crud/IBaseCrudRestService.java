package ir.amv.os.vaseline.ws.rest.apis.basic.layer.crud;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.common.apis.basic.layer.crud.IBaseCrudWebService;
import ir.amv.os.vaseline.ws.common.apis.basic.layer.ro.IBaseReadOnlyWebService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

/**
 * @author Amir
 */
public interface IBaseCrudRestService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseCrudWebService<D, Id>, IBaseReadOnlyWebService<D, Id> {

    @POST
    @Path("/")
    Id save(D t) throws BaseVaselineClientException;

    @PUT
    @Path("/")
    void update(D t) throws BaseVaselineClientException;

    @POST
    @Path("/deleteEntity")
    void delete(D id) throws BaseVaselineClientException;

    @DELETE
    @Path("/{id}")
    void deleteById(Id id) throws BaseVaselineClientException;
}
