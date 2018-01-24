package ir.amv.os.vaseline.business.osgi.executor;

import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessExecutorInterceptor;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.executor.IImplementedBusinessActionExecutor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineBusinessActionExecutor.class
)
public class VaselineBusinessActionExecutorImpl
        implements IVaselineBusinessActionExecutor, IImplementedBusinessActionExecutor {
    private List<IVaselineBusinessExecutorInterceptor> interceptors = new ArrayList<>();

    @Override
    public List<IVaselineBusinessExecutorInterceptor> getBusinessExecutorInterceptors() {
        return interceptors;
    }

    @Override
    @Reference(
            cardinality = ReferenceCardinality.AT_LEAST_ONE,
            policyOption = ReferencePolicyOption.GREEDY
    )
    public void addBusinessInterceptor(final IVaselineBusinessExecutorInterceptor interceptor) {
        IImplementedBusinessActionExecutor.super.addBusinessInterceptor(interceptor);
    }

    @Override
    public void removeBusinessInterceptor(final IVaselineBusinessExecutorInterceptor interceptor) {
        IImplementedBusinessActionExecutor.super.removeBusinessInterceptor(interceptor);
    }
}
