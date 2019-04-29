package ir.amv.os.vaseline.basics.karaf.feature.proxy;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.server.proxy.IProxyInterceptor;
import ir.amv.os.vaseline.basics.core.api.server.proxy.MethodExecution;

public class ProxyInterceptor implements IProxyInterceptor<String> {

    private boolean preExecuteCalled;
    private boolean postExecuteSuccessCalled;
    private boolean postExecuteExceptionCalled;

    @Override
    public boolean appliesTo(MethodExecution methodExecution) {
        return true;
    }

    @Override
    public String preExecute(MethodExecution methodExecution) throws BaseVaselineServerException {
        preExecuteCalled = true;
        return "";
    }

    @Override
    public <R> void postExecuteSuccessfully(MethodExecution methodExecution, R returnedValue, String preExecResult) throws BaseVaselineServerException {
        postExecuteSuccessCalled = true;
    }

    @Override
    public void postExecuteException(MethodExecution methodExecution, Throwable t, String preExecResult) throws BaseVaselineServerException {
        postExecuteExceptionCalled = true;
    }

    public boolean isPreExecuteCalled() {
        return preExecuteCalled;
    }

    public boolean isPostExecuteExceptionCalled() {
        return postExecuteExceptionCalled;
    }

    public boolean isPostExecuteSuccessCalled() {
        return postExecuteSuccessCalled;
    }
}
