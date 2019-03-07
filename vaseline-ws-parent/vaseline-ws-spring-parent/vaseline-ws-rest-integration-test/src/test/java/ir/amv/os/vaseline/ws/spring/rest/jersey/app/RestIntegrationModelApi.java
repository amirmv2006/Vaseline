package ir.amv.os.vaseline.ws.spring.rest.jersey.app;

import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.crud.IBaseImplementedCrudApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Amir
 */
@Service
public class RestIntegrationModelApi
        extends ProxyAwareImpl
        implements IRestIntegrationModelApi,
        IBaseImplementedCrudApi<RestIntegrationModelEntity, Long, IRestIntegrationModelDao> {
    @Autowired
    private IVaselineBusinessActionExecutor businessActionExecutor;
    @Autowired
    private IRestIntegrationModelDao dao;

    @Override
    public IVaselineBusinessActionExecutor getBusinessActionExecutor() {
        return businessActionExecutor;
    }

    @Override
    public IRestIntegrationModelDao getDao() {
        return dao;
    }

}
