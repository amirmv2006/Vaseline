package ir.amv.os.vaseline.basics.core.osgi.proxy;

import ir.amv.os.vaseline.basics.core.api.server.proxy.IProxyInterceptor;
import ir.amv.os.vaseline.basics.core.api.server.proxy.MethodExecution;
import org.apache.aries.proxy.InvocationListener;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ProxyInterceptor implements InvocationListener {

    private Object originalObject;
    private ProxyEventListenerHook proxyHook;

    public ProxyInterceptor(Object originalObject, ProxyEventListenerHook proxyHook) {
        this.originalObject = originalObject;
        this.proxyHook = proxyHook;
    }

    @Override
    public Object preInvoke(Object proxy, Method m, Object[] args) throws Throwable {
        Map<InterceptorToken, Object> preCallMap = new HashMap<>();
        for (IProxyInterceptor interceptor : proxyHook.getProxyInterceptors()) {
            MethodExecution methodExecution = new MethodExecution(proxy, originalObject, m, args);
            if (interceptor.appliesTo(methodExecution)) {
                preCallMap.put(new InterceptorToken(interceptor, methodExecution), interceptor.preExecute(methodExecution));
            }
        }
        return preCallMap;
    }

    @Override
    public void postInvoke(Object token, Object proxy, Method m, Object returnValue) throws Throwable {
        Map<InterceptorToken, Object> preCallMap = (Map<InterceptorToken, Object>) token;
        for (InterceptorToken interceptorToken : preCallMap.keySet()) {
            interceptorToken.interceptor.postExecuteSuccessfully(interceptorToken.methodExecution, returnValue, preCallMap.get(interceptorToken));
        }
    }

    @Override
    public void postInvokeExceptionalReturn(Object token, Object proxy, Method m, Throwable exception) throws Throwable {
        Map<InterceptorToken, Object> preCallMap = (Map<InterceptorToken, Object>) token;
        for (InterceptorToken interceptorToken : preCallMap.keySet()) {
            interceptorToken.interceptor.postExecuteException(interceptorToken.methodExecution, exception, preCallMap.get(interceptorToken));
        }
    }

    private static class InterceptorToken {
        IProxyInterceptor interceptor;
        MethodExecution methodExecution;

        public InterceptorToken(IProxyInterceptor interceptor, MethodExecution methodExecution) {
            this.interceptor = interceptor;
            this.methodExecution = methodExecution;
        }
    }
}
