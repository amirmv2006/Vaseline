package ir.amv.os.vaseline.service.basic.def.server.ro.impl;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api.IDefaultModelConverter;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.service.basic.def.server.base.impl.BaseServiceImpl;
import ir.amv.os.vaseline.service.basic.def.server.ro.IDefaultReadOnlyService;
import org.springframework.core.convert.ConversionService;

import javax.validation.Validator;
import java.io.Serializable;

public class BaseReadOnlyServiceImpl<
            I extends Serializable,
            D extends IBaseDto<I>,
            M extends IBaseBusinessModel<I>,
            A extends IBaseReadOnlyApi<I, M>
        >
        extends BaseServiceImpl
        implements IDefaultReadOnlyService<I, D, M, A>, IDefaultModelConverter<D, M> {

    private final A api;

    public BaseReadOnlyServiceImpl(Validator validator, ConversionService conversionService, A api) {
        super(validator, conversionService);
        this.api = api;
    }

    @Override
    public A getApi() {
        return api;
    }

}
