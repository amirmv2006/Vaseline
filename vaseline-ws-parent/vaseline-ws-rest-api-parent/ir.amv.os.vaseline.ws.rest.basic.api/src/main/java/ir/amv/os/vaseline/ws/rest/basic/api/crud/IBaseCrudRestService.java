package ir.amv.os.vaseline.ws.rest.basic.api.crud;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
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
public interface IBaseCrudRestService<Id extends Serializable, D extends IBaseDto<Id>>
        extends IBaseCrudWebService<Id, D>, IBaseReadOnlyWebService<Id, D> {

    @POST
    @Path("/")
    Id save(D t) throws BaseExternalException;

    @PUT
    @Path("/")
    void update(D t) throws BaseExternalException;

    @POST
    @Path("/deleteEntity")
    void delete(D id) throws BaseExternalException;

    @DELETE
    @Path("/{id}")
    void deleteById(Id id) throws BaseExternalException;
}
