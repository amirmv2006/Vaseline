package ir.amv.os.vaseline.service.osgi.feature.search.advanced.layer;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.mapper.api.server.objmapper.IVaselineObjectMapper;
import ir.amv.os.vaseline.business.osgi.feature.search.advanced.layer.ISampleAdvancedSearchApi;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleDto;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleSearchObject;
import ir.amv.os.vaseline.service.search.advanced.def.server.IDefaultAdvancedSearchService;

import javax.validation.Validator;

public class SampleAdvancedSearchServiceImpl
        implements IDefaultAdvancedSearchService<Long, SampleEntity, SampleDto, SampleSearchObject, ISampleAdvancedSearchApi>,
        ISampleAdvancedSearchService {
    private ISampleAdvancedSearchApi api;
    private IVaselineObjectMapper mapper;
    private Validator validator;
    private ICoreExceptionHandler coreExceptionHandler;

    public SampleAdvancedSearchServiceImpl(ISampleAdvancedSearchApi api, IVaselineObjectMapper mapper, Validator validator, ICoreExceptionHandler coreExceptionHandler) {
        this.api = api;
        this.mapper = mapper;
        this.validator = validator;
        this.coreExceptionHandler = coreExceptionHandler;
    }

    @Override
    public ISampleAdvancedSearchApi getApi() {
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
