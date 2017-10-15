package ir.amv.os.vaseline.service.apis.advancedsearch.layer.server;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.apis.layer.server.ro.IBaseReadOnlyService;

import java.io.Serializable;
import java.util.List;

public interface IBaseAdvancedSearchService<D extends IBaseDto<Id>, SO extends IBaseSearchObject, Id extends Serializable>
        extends IBaseReadOnlyService<D, Id> {

    Long countBySearchObject(SO searchObject) throws BaseVaselineClientException;
    List<D> searchBySearchObject(SO searchObject) throws BaseVaselineClientException;
    List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseVaselineClientException;
}
