package ir.amv.os.vaseline.service.search.advanced.api.server;

import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.data.advanced.search.api.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.api.layer.ro.IBaseReadOnlyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IBaseAdvancedSearchService<Id extends Serializable, D extends IBaseDto<Id>, SO extends IBaseSearchObject>
        extends IBaseReadOnlyService<Id, D> {

    Long countBySearchObject(SO searchObject) throws BaseExternalException;
    List<D> searchBySearchObject(SO searchObject) throws BaseExternalException;
    Page<D> searchBySearchObject(SO searchObject, Pageable pagingDto) throws BaseExternalException;
}
