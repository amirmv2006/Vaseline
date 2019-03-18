package ir.amv.os.vaseline.ws.common.search.advanced.api;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.ws.common.basic.api.ro.IBaseReadOnlyWebService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseAdvancedSearchWebService<D extends IBaseDto<Id>, SO extends IBaseSearchObject, Id extends Serializable>
        extends IBaseReadOnlyWebService<D, Id> {

    Long countBySearchObject(SO searchObject) throws BaseVaselineClientException;
    List<D> searchBySearchObject(SO searchObject) throws BaseVaselineClientException;
    List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseVaselineClientException;

}
