package ir.amv.os.vaseline.ws.rest.apis.advancedsearch.layer;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.ws.common.apis.advancedsearch.layer.IBaseAdvancedSearchWebService;
import ir.amv.os.vaseline.ws.rest.apis.basic.layer.ro.IBaseReadOnlyRestService;

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
