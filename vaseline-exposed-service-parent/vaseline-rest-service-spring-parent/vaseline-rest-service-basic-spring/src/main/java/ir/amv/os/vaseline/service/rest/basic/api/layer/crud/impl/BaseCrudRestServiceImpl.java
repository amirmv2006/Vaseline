package ir.amv.os.vaseline.service.rest.basic.api.layer.crud.impl;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.business.basic.api.layer.crud.IBaseCrudApi;
import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.def.server.crud.IDefaultCrudService;
import ir.amv.os.vaseline.service.rest.basic.api.layer.crud.IBaseCrudRestService;
import ir.amv.os.vaseline.service.rest.basic.api.layer.ro.impl.BaseReadOnlyRestServiceImpl;
import org.springframework.core.convert.ConversionService;

import javax.validation.Validator;
import java.io.Serializable;

public class BaseCrudRestServiceImpl<
            I extends Serializable,
            D extends IBaseDto<I>,
            M extends IBaseBusinessModel<I>,
            A extends IBaseCrudApi<I, M>
        >
        extends BaseReadOnlyRestServiceImpl<I, D, M, A>
        implements IDefaultCrudService<I, D, M, A>,
        IBaseCrudRestService<I, D> {

    public BaseCrudRestServiceImpl(Validator validator, ConversionService conversionService, A api) {
        super(validator, conversionService, api);
    }

    @Override
    public I save(D t) throws BaseExternalException {
        return IDefaultCrudService.super.save(t);
    }

    @Override
    public void update(D t) throws BaseExternalException {
        IDefaultCrudService.super.update(t);
    }

    @Override
    public void delete(D id) throws BaseExternalException {
        IDefaultCrudService.super.delete(id);
    }

    @Override
    public void deleteById(I id) throws BaseExternalException {
        IDefaultCrudService.super.deleteById(id);
    }
}
