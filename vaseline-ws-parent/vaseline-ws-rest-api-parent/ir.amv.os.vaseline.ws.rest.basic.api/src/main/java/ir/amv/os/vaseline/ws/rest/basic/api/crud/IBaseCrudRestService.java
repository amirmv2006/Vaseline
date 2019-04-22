package ir.amv.os.vaseline.ws.rest.basic.api.crud;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.common.basic.api.crud.IBaseCrudWebService;
import ir.amv.os.vaseline.ws.common.basic.api.ro.IBaseReadOnlyWebService;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
