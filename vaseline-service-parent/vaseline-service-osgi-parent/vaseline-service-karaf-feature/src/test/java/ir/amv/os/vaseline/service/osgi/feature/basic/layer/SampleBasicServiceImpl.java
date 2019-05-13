package ir.amv.os.vaseline.service.osgi.feature.basic.layer;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.mapper.api.server.objmapper.IVaselineObjectMapper;
import ir.amv.os.vaseline.business.osgi.feature.basic.layer.ISampleBasicApi;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleDto;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.service.basic.def.server.crud.IDefaultCrudService;

import javax.validation.Validator;

public class SampleBasicServiceImpl
        implements IDefaultCrudService<Long, SampleEntity, SampleDto, ISampleBasicApi>,
        ISampleBasicService {
    private ISampleBasicApi api;
    private IVaselineObjectMapper mapper;
    private Validator validator;
    private ICoreExceptionHandler coreExceptionHandler;

    public SampleBasicServiceImpl(ISampleBasicApi api, IVaselineObjectMapper mapper, Validator validator, ICoreExceptionHandler coreExceptionHandler) {
        this.api = api;
        this.mapper = mapper;
        this.validator = validator;
        this.coreExceptionHandler = coreExceptionHandler;
    }

    @Override
    public ISampleBasicApi getApi() {
        return api;
    }

    @Override
    public IVaselineObjectMapper getMapper() {
        return mapper;
    }

    @Override
    public Validator getValidator() {
        return validator;
    }

    @Override
    public ICoreExceptionHandler getCoreExceptionHandler() {
        return coreExceptionHandler;
    }
}
