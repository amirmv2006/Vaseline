package ir.amv.os.vaseline.ws.rest.search.simple.api;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.common.search.simple.api.IBaseSimpleSearchWebService;
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
public interface IBaseSimpleSearchRestService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseSimpleSearchWebService<D, Id>, IBaseReadOnlyRestService<D, Id> {

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/count")
    Long countByExample(D example) throws BaseVaselineClientException;
    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/all")
    List<D> searchByExample(D example) throws BaseVaselineClientException;
    @Override
    List<D> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineClientException;

}
