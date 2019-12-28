package ir.amv.os.vaseline.service.basic.def.server.crud.impl;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.business.basic.api.layer.crud.IBaseCrudApi;
import ir.amv.os.vaseline.service.basic.def.server.crud.IDefaultCrudService;
import ir.amv.os.vaseline.service.basic.def.server.ro.impl.BaseReadOnlyServiceImpl;
import org.springframework.core.convert.ConversionService;

import javax.validation.Validator;
import java.io.Serializable;

public class BaseCrudServiceImpl<
        I extends Serializable,
        D extends IBaseDto<I>,
        M extends IBaseBusinessModel<I>,
        A extends IBaseCrudApi<I, M>
        >
        extends BaseReadOnlyServiceImpl<I, D, M, A>
        implements IDefaultCrudService<I, D, M, A> {

    public BaseCrudServiceImpl(Validator validator, ConversionService conversionService, A api) {
        super(validator, conversionService, api);
    }
}
