package ir.amv.os.vaseline.service.search.advanced.api.server;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.basic.api.server.ro.IBaseReadOnlyService;

import java.io.Serializable;
import java.util.List;

public interface IBaseAdvancedSearchService<Id extends Serializable, D extends IBaseDto<Id>, SO extends IBaseSearchObject>
        extends IBaseReadOnlyService<Id, D> {

    Long countBySearchObject(SO searchObject) throws BaseExternalException;
    List<D> searchBySearchObject(SO searchObject) throws BaseExternalException;
    List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseExternalException;
}
