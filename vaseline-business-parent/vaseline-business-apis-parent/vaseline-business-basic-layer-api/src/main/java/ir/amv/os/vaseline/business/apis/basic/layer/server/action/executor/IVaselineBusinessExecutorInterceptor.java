package ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;

/**
 * @author Amir
 */
public interface IVaselineBusinessExecutorInterceptor<T> {

    <R> boolean appliesTo(IBusinessAction<R> businessAction);

    <R> T preExecute(IBusinessAction<R> businessAction) throws BaseVaselineServerException;
    <R> void postExecuteSuccessfully(final IBusinessAction<R> businessAction, R returnedValue, final T
            preExecResult) throws BaseVaselineServerException;
    <R> void postExecuteException(final IBusinessAction<R> businessAction, Throwable t, final T preExecResult) throws
            BaseVaselineServerException;

    Class<T> tokenClass();
}
