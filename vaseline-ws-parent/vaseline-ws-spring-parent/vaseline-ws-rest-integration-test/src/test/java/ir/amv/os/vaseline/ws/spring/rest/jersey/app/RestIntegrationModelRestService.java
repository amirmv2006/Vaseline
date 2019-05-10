package ir.amv.os.vaseline.ws.spring.rest.jersey.app;

import ir.amv.os.vaseline.ws.rest.basic.def.crud.IDefaultCrudRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Amir
 */
@Component
public class RestIntegrationModelRestService
        implements IRestIntegrationModelRestService,
        IDefaultCrudRestService<Long, RestIntegrationModelDto, IRestIntegrationModelService> {

    @Autowired
    private IRestIntegrationModelService service;

    @Override
    public IRestIntegrationModelService getService() {
        return service;
    }
}
