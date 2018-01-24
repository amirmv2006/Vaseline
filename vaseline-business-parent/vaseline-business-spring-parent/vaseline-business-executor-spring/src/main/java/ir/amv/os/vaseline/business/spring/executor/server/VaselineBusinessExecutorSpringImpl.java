package ir.amv.os.vaseline.business.spring.executor.server;

import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessExecutorInterceptor;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.executor.IImplementedBusinessActionExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Amir
 */
@Component
public class VaselineBusinessExecutorSpringImpl
        implements IImplementedBusinessActionExecutor {
    private List<IVaselineBusinessExecutorInterceptor> interceptors;

    @Override
    public List<IVaselineBusinessExecutorInterceptor> getBusinessExecutorInterceptors() {
        return interceptors;
    }

    @Autowired
    public void setInterceptors(final List<IVaselineBusinessExecutorInterceptor> interceptors) {
        this.interceptors = interceptors;
    }
}
