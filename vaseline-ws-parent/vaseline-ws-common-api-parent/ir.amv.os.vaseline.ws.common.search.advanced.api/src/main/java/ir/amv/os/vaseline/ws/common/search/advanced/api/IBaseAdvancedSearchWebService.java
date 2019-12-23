package ir.amv.os.vaseline.ws.common.search.advanced.api;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.ws.common.basic.api.ro.IBaseReadOnlyWebService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseAdvancedSearchWebService<Id extends Serializable, D extends IBaseDto<Id>, SO extends IBaseSearchObject>
        extends IBaseReadOnlyWebService<Id, D> {

    Long countBySearchObject(SO searchObject) throws BaseExternalException;
    List<D> searchBySearchObject(SO searchObject) throws BaseExternalException;
    List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseExternalException;

}
