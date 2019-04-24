package ir.amv.os.vaseline.basics.core.api.server.proxy;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;

public interface IProxyInterceptor<K> {
    default int priority() {
        return 0;
    }

    boolean appliesTo(MethodExecution methodExecution);

    K preExecute(MethodExecution methodExecution) throws BaseVaselineServerException;

    <R> void postExecuteSuccessfully(final MethodExecution methodExecution, R returnedValue, final K
            preExecResult) throws BaseVaselineServerException;

    void postExecuteException(final MethodExecution methodExecution, Throwable t, final K preExecResult) throws
            BaseVaselineServerException;

}
