package ir.amv.os.vaseline.service.rest.advanced.search.layer;

import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.data.advanced.search.api.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;
import ir.amv.os.vaseline.service.search.advanced.api.server.IBaseAdvancedSearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;
import java.util.List;

public interface IBaseAdvancedSearchRestService<I extends Serializable, D extends IBaseDto<I>, S extends IBaseSearchObject>
        extends IBaseAdvancedSearchService<I, D, S> {

    @Override
    @PostMapping(path = "searchCount")
    Long countBySearchObject(S searchObject) throws BaseExternalException;

    @Override
    @PostMapping(path = "searchAll")
    List<D> searchBySearchObject(S searchObject) throws BaseExternalException;

    @Override
    @PostMapping(path = "searchPage")
    Page<D> searchBySearchObject(S searchObject, Pageable pagingDto) throws BaseExternalException;
}
