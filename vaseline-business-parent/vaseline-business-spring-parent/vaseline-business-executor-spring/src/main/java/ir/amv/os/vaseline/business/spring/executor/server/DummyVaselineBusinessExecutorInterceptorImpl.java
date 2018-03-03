package ir.amv.os.vaseline.business.spring.executor.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessExecutorInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author Amir
 */
@Component
public class DummyVaselineBusinessExecutorInterceptorImpl
        implements IVaselineBusinessExecutorInterceptor<Void> {
    @Override
    public <R> boolean appliesTo(final IBusinessAction<R> businessAction) {
        return false;
    }

    @Override
    public <R> Void preExecute(final IBusinessAction<R> businessAction) throws BaseVaselineServerException {
        return null;
    }

    @Override
    public <R> void postExecuteSuccessfully(final IBusinessAction<R> businessAction, final R returnedValue, final Void preExecResult) throws BaseVaselineServerException {

    }

    @Override
    public <R> void postExecuteException(final IBusinessAction<R> businessAction, final Throwable t, final Void preExecResult) throws BaseVaselineServerException {

    }

    @Override
    public Class<Void> tokenClass() {
        return Void.class;
    }
}