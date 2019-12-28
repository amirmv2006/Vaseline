package ir.amv.os.vaseline.service.basic.def.server.base.impl;

import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api.IDefaultConverterSpringApi;
import ir.amv.os.vaseline.service.basic.def.server.base.IDefaultService;
import org.springframework.core.convert.ConversionService;

import javax.validation.Validator;

public class BaseServiceImpl
        implements IDefaultService, IDefaultConverterSpringApi {

    private final Validator validator;
    private final ConversionService conversionService;

    public BaseServiceImpl(Validator validator, ConversionService conversionService) {
        this.validator = validator;
        this.conversionService = conversionService;
    }

    @Override
    public Validator getValidator() {
        return validator;
    }

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

}
