package ir.amv.os.vaseline.business.spring.executor.server;

import ir.amv.os.vaseline.business.basic.api.server.action.executor.IVaselineBusinessExecutorInterceptor;
import ir.amv.os.vaseline.business.basic.def.server.action.executor.IDefaultActionExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Amir
 */
@Component
public class VaselineBusinessExecutorSpringImpl
        implements IDefaultActionExecutor {
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
