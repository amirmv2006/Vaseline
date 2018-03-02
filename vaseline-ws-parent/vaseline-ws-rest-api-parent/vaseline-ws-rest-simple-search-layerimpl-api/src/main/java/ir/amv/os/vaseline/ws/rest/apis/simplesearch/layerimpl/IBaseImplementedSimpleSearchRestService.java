package ir.amv.os.vaseline.ws.rest.apis.simplesearch.layerimpl;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.apis.simplesearch.layer.server.IBaseSimpleSearchService;
import ir.amv.os.vaseline.ws.common.apis.simplesearch.layerimpl.IBaseImplementedSimpleSearchWebService;
import ir.amv.os.vaseline.ws.rest.apis.basic.layerimpl.ro.IBaseImplementedReadOnlyRestService;
import ir.amv.os.vaseline.ws.rest.apis.simplesearch.layer.IBaseSimpleSearchRestService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseImplementedSimpleSearchRestService<D extends IBaseDto<Id>, Id extends Serializable, Service extends
        IBaseSimpleSearchService<D, Id>>
        extends IBaseSimpleSearchRestService<D, Id>,
        IBaseImplementedReadOnlyRestService<D, Id, Service>,
        IBaseImplementedSimpleSearchWebService<D, Id, Service> {

    @Override
    default Long countByExample(D example) throws BaseVaselineClientException {
        return IBaseImplementedSimpleSearchWebService.super.countByExample(example);
    }

    @Override
    default List<D> searchByExample(D example) throws BaseVaselineClientException {
        return IBaseImplementedSimpleSearchWebService.super.searchByExample(example);
    }

    @Override
    default List<D> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineClientException {
        return IBaseImplementedSimpleSearchWebService.super.searchByExample(example, pagingDto);
    }

}
