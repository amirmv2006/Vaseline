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
public interface IDefaultSimpleSearchWebService<Id extends Serializable, D extends IBaseDto<Id>, Service
        extends IBaseSimpleSearchService<Id, D>>
        extends IBaseSimpleSearchWebService<Id, D>, IDefaultReadOnlyWebService<Id, D, Service> {

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
