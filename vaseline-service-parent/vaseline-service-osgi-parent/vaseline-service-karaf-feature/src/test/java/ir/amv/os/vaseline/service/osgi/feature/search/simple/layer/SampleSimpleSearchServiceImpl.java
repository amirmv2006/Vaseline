package ir.amv.os.vaseline.service.osgi.feature.search.simple.layer;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.mapper.api.server.objmapper.IVaselineObjectMapper;
import ir.amv.os.vaseline.business.osgi.feature.search.simple.layer.ISampleSimpleSearchApi;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleDto;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.service.search.simple.def.server.IDefaultSimpleSearchService;

import javax.validation.Validator;

public class SampleSimpleSearchServiceImpl
        implements IDefaultSimpleSearchService<Long, SampleEntity, SampleDto, ISampleSimpleSearchApi>,
        ISampleSimpleSearchService {
    private ISampleSimpleSearchApi api;
    private IVaselineObjectMapper mapper;
    private Validator validator;
    private ICoreExceptionHandler coreExceptionHandler;

    public SampleSimpleSearchServiceImpl(ISampleSimpleSearchApi api, IVaselineObjectMapper mapper, Validator validator, ICoreExceptionHandler coreExceptionHandler) {
        this.api = api;
        this.mapper = mapper;
        this.validator = validator;
        this.coreExceptionHandler = coreExceptionHandler;
    }

    @Override
    public ISampleSimpleSearchApi getApi() {
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
