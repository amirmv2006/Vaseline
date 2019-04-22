package ir.amv.os.vaseline.ws.common.search.simple.def;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.search.simple.api.server.IBaseSimpleSearchService;
import ir.amv.os.vaseline.ws.common.basic.def.ro.IDefaultReadOnlyWebService;
import ir.amv.os.vaseline.ws.common.search.simple.api.IBaseSimpleSearchWebService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultSimpleSearchWebService<D extends IBaseDto<Id>, Id extends Serializable, Service
        extends IBaseSimpleSearchService<D, Id>>
        extends IBaseSimpleSearchWebService<D, Id>, IDefaultReadOnlyWebService<D, Id, Service> {

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
