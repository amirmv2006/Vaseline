package ir.amv.os.vaseline.service.rest.advanced.search.layer.impl;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.business.search.advanced.api.server.IBaseAdvancedSearchApi;
import ir.amv.os.vaseline.data.advanced.search.api.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;
import ir.amv.os.vaseline.service.rest.advanced.search.layer.IBaseAdvancedSearchRestService;
import ir.amv.os.vaseline.service.rest.basic.api.layer.ro.impl.BaseReadOnlyRestServiceImpl;
import ir.amv.os.vaseline.service.search.advanced.def.server.IDefaultAdvancedSearchService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Validator;
import java.io.Serializable;
import java.util.List;

public class BaseAdvancedSearchRestServiceImpl<
        I extends Serializable,
        D extends IBaseDto<I>,
        M extends IBaseBusinessModel<I>,
        S extends IBaseSearchObject,
        A extends IBaseAdvancedSearchApi<I, M, S>>
        extends BaseReadOnlyRestServiceImpl<I, D, M, A>
        implements IBaseAdvancedSearchRestService<I, D, S>,
        IDefaultAdvancedSearchService<I, D, M, S, A> {

    public BaseAdvancedSearchRestServiceImpl(Validator validator, ConversionService conversionService, A api) {
        super(validator, conversionService, api);
    }

    @Override
    public Long countBySearchObject(S searchObject) throws BaseExternalException {
        return IDefaultAdvancedSearchService.super.countBySearchObject(searchObject);
    }

    @Override
    public List<D> searchBySearchObject(S searchObject) throws BaseExternalException {
        return IDefaultAdvancedSearchService.super.searchBySearchObject(searchObject);
    }

    @Override
    public Page<D> searchBySearchObject(S searchObject, Pageable pagingDto) throws BaseExternalException {
        return IDefaultAdvancedSearchService.super.searchBySearchObject(searchObject, pagingDto);
    }
}
