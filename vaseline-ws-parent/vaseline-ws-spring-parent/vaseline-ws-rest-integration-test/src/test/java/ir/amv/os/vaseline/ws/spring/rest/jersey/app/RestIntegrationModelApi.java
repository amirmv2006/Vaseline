package ir.amv.os.vaseline.ws.spring.rest.jersey.app;

import ir.amv.os.vaseline.basics.core.api.server.proxy.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.business.basic.def.server.crud.IDefaultCrudApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Amir
 */
@Service
public class RestIntegrationModelApi
        extends ProxyAwareImpl
        implements IRestIntegrationModelApi,
        IDefaultCrudApi<Long, RestIntegrationModelEntity, IRestIntegrationModelDao> {
    @Autowired
    private IRestIntegrationModelDao dao;

    @Override
    public IRestIntegrationModelDao getDao() {
        return dao;
    }

}
