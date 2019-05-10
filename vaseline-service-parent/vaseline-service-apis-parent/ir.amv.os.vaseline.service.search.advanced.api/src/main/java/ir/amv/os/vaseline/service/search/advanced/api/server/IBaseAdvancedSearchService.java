package ir.amv.os.vaseline.service.search.advanced.api.server;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.basic.api.server.ro.IBaseReadOnlyService;

import java.io.Serializable;
import java.util.List;

public interface IBaseAdvancedSearchService<Id extends Serializable, D extends IBaseDto<Id>, SO extends IBaseSearchObject>
        extends IBaseReadOnlyService<Id, D> {

    Long countBySearchObject(SO searchObject) throws BaseVaselineClientException;
    List<D> searchBySearchObject(SO searchObject) throws BaseVaselineClientException;
    List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseVaselineClientException;
}
