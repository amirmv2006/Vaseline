package ir.amv.os.vaseline.ws.rest.search.simple.api;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
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
public interface IBaseSimpleSearchRestService<Id extends Serializable, D extends IBaseDto<Id>>
        extends IBaseSimpleSearchWebService<Id, D>, IBaseReadOnlyRestService<Id, D> {

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
