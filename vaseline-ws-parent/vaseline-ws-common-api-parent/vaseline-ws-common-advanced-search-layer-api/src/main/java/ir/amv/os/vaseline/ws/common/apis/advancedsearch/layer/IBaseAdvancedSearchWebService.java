package ir.amv.os.vaseline.ws.common.apis.advancedsearch.layer;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.ws.common.apis.basic.layer.ro.IBaseReadOnlyWebService;

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
