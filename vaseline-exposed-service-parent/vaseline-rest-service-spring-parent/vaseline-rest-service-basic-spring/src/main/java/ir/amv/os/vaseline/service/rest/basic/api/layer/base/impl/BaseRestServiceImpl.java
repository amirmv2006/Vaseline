package ir.amv.os.vaseline.service.rest.basic.api.layer.base.impl;

import ir.amv.os.vaseline.service.basic.def.server.base.impl.BaseServiceImpl;
import ir.amv.os.vaseline.service.rest.basic.api.layer.base.IBaseRestService;
import org.springframework.core.convert.ConversionService;

import javax.validation.Validator;

public class BaseRestServiceImpl
        extends BaseServiceImpl
        implements IBaseRestService {

    public BaseRestServiceImpl(Validator validator, ConversionService conversionService) {
        super(validator, conversionService);
    }
}
