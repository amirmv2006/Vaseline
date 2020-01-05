package ir.amv.os.vaseline.service.rest.basic.api.layer.ro.impl;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessEntity;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.def.server.ro.impl.BaseReadOnlyServiceImpl;
import ir.amv.os.vaseline.service.rest.basic.api.layer.ro.IBaseReadOnlyRestService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Validator;
import java.io.Serializable;
import java.util.List;

public class BaseReadOnlyRestServiceImpl<
            I extends Serializable,
            D extends IBaseDto<I>,
            M extends IBaseBusinessEntity<I, M>,
            A extends IBaseReadOnlyApi<I, M>
        >
        extends BaseReadOnlyServiceImpl<I, D, M, A>
        implements IBaseReadOnlyRestService<I, D> {

    public BaseReadOnlyRestServiceImpl(Validator validator, ConversionService conversionService, A api) {
        super(validator, conversionService, api);
    }

    @Override
    public D getById(I id) throws BaseExternalException {
        return super.getById(id);
    }

    @Override
    public Long countAll() throws BaseExternalException {
        return super.countAll();
    }

    @Override
    public List<D> getAll() throws BaseExternalException {
        return super.getAll();
    }

    @Override
    public Page<D> getAll(Pageable pagingDto) throws BaseExternalException {
        return super.getAll(pagingDto);
    }
}
