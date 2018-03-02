package ir.amv.os.vaseline.ws.common.apis.simplesearch.layerimpl;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.apis.simplesearch.layer.server.IBaseSimpleSearchService;
import ir.amv.os.vaseline.ws.common.apis.basic.layerimpl.ro.IBaseImplementedReadOnlyWebService;
import ir.amv.os.vaseline.ws.common.apis.simplesearch.layer.IBaseSimpleSearchWebService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseImplementedSimpleSearchWebService<D extends IBaseDto<Id>, Id extends Serializable, Service
        extends IBaseSimpleSearchService<D, Id>>
        extends IBaseSimpleSearchWebService<D, Id>, IBaseImplementedReadOnlyWebService<D, Id, Service> {

    @Override
    default Long countByExample(D example) throws BaseVaselineClientException {
        return getService().countByExample(example);
    }

    @Override
    default List<D> searchByExample(D example) throws BaseVaselineClientException {
        return getService().searchByExample(example);
    }

    @Override
    default List<D> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineClientException {
        return getService().searchByExample(example, pagingDto);
    }
}
