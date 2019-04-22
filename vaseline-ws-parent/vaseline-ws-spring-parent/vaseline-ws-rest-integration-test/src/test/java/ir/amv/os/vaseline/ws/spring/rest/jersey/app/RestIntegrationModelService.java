package ir.amv.os.vaseline.ws.spring.rest.jersey.app;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.mapper.api.server.objmapper.IVaselineObjectMapper;
import ir.amv.os.vaseline.service.basic.def.server.crud.IDefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

/**
 * @author Amir
 */
@Service
public class RestIntegrationModelService
        implements IRestIntegrationModelService,
        IDefaultCrudService<RestIntegrationModelEntity, RestIntegrationModelDto, Long, IRestIntegrationModelApi> {
    @Autowired
    private IRestIntegrationModelApi api;
    @Autowired
    private IVaselineObjectMapper mapper;
    @Autowired
    private Validator validator;
    @Autowired
    private ICoreExceptionHandler coreExceptionHandler;

    @Override
    public IRestIntegrationModelApi getApi() {
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
