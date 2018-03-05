package ir.amv.os.vaseline.business.osgi.executor;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.osgi.logging.common.server.helper.LOGGER;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.AbstractBusinessActionImpl;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessExecutorInterceptor;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.IBusinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.executor.IImplementedBusinessActionExecutor;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.apache.aries.proxy.InvocationListener;
import org.apache.aries.proxy.ProxyManager;
import org.apache.aries.proxy.UnableToProxyException;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.util.tracker.ServiceTracker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineBusinessActionExecutor.class
)
public class VaselineBusinessActionExecutorImpl
        implements IVaselineBusinessActionExecutor, InvocationListener {
    private ProxyManager proxyManager;
    private ServiceTracker<IProxyAware, IProxyAware> serviceTracker;
    private List<IVaselineBusinessExecutorInterceptor> interceptors = new ArrayList<>();

    public List<IVaselineBusinessExecutorInterceptor> getBusinessExecutorInterceptors() {
        return interceptors;
    }

    @Activate
    public void init(ComponentContext componentContext) {
        serviceTracker = new ServiceTracker<IProxyAware, IProxyAware>(componentContext.getBundleContext(), IProxyAware.class, null) {
            @Override
            public IProxyAware addingService(final ServiceReference<IProxyAware> reference) {
                IProxyAware proxyAware = super.addingService(reference);
                if (proxyAware == null) {
                    LOGGER.log(VaselineLogLevel.ERROR, "problem with service reference %s", reference);
                } else {
                    LOGGER.log(VaselineLogLevel.INFO, "proxying service with reference %s", reference);
                    try {
                        proxy(proxyAware, reference.getBundle());
                    } catch (UnableToProxyException e) {
                        LOGGER.log(VaselineLogLevel.ERROR, "unable to proxy service with reference %s", reference);
                    }
                }
                return proxyAware;
            }
        };
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                serviceTracker.open();
            }
        }, 2000);
    }

    @Deactivate
    public void finish() {
        serviceTracker.close();
    }

    private void proxy(final IProxyAware proxyAware, final Bundle bundle) throws UnableToProxyException {
        LOGGER.log("Creating proxy for %s", proxyAware.getClass());
        Collection<Class<?>> proxyClasses = Arrays.asList(proxyAware.getClass().getInterfaces());
        Object proxy = proxyManager.createDelegatingInterceptingProxy(
                bundle,
                proxyClasses,
                () -> proxyAware,
                proxyAware,
                this
        );
        LOGGER.log("Proxy %s ready for %s", proxy, proxyAware);
        proxyAware.setProxy(proxy);
    }

    @Reference(
            cardinality = ReferenceCardinality.AT_LEAST_ONE,
            policyOption = ReferencePolicyOption.GREEDY
    )
    public void addBusinessInterceptor(final IVaselineBusinessExecutorInterceptor interceptor) {
        if (!interceptors.contains(interceptor)) {
            interceptors.add(interceptor);
        }
    }

    public void removeBusinessInterceptor(final IVaselineBusinessExecutorInterceptor interceptor) {
        interceptors.remove(interceptor);
    }

    @Override
    public <R> R executeAction(final IBusinessAction<R> action) throws BaseVaselineServerException {
        return action.execute(this);
    }

    @Reference
    public void setProxyManager(final ProxyManager proxyManager) {
        this.proxyManager = proxyManager;
    }

    @Override
    public Object preInvoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        IBusinessAction<Object> action = getBusinessAction(proxy, method, args);
        Map<TokenMapKey, Object> token = new HashMap<>();
        for (IVaselineBusinessExecutorInterceptor interceptor : interceptors) {
            if (interceptor.appliesTo(action)) {
                Object preExecuteResult = interceptor.preExecute(action);
                token.put(new TokenMapKey(interceptor.tokenClass(), action), preExecuteResult);
            }
        }
        return token;
    }

    private IBusinessAction<Object> getBusinessAction(final Object proxy, final Method method, final Object[] args) throws BaseVaselineServerException {
        IBusinessMetadata[] metadata;
        VaselineBuinessMetadata buinessMetadata = ReflectionUtil.getMethodAnnotationInHierarchy
                (VaselineBuinessMetadata.class, proxy.getClass(), method.getName(), method.getParameterTypes());
        if (buinessMetadata != null) {
            VaselineAllBuinessMetadata[] businessMetadata = buinessMetadata.value();
            metadata = new IBusinessMetadata[buinessMetadata.value().length];
            for (int i = 0; i < metadata.length; i++) {
                metadata[i] = buinessMetadata.value()[i];
            }
        } else {
            metadata = new IBusinessMetadata[0];
        }
        return new AbstractBusinessActionImpl<Object>(
                UUID.randomUUID().toString(),
                method.getDeclaringClass(),
                method,
                args,
                metadata,
                method.getName(),
                (Class<Object>) method.getReturnType()
        ) {
            @Override
            public Object execute(final IVaselineBusinessActionExecutor businessActionExecutor) throws BaseVaselineServerException {
                try {
                    return method.invoke(proxy, args);
                } catch (Exception e) {
                    throw new BaseVaselineServerException(e);
                }
            }
        };
    }

    @Override
    public void postInvoke(Object token, Object proxy, Method m, Object returnValue) throws Throwable {
        Map<TokenMapKey, Object> tokenMap = (Map<TokenMapKey, Object>) token;
        for (IVaselineBusinessExecutorInterceptor interceptor : interceptors) {
            Set<Map.Entry<TokenMapKey, Object>> entries = tokenMap.entrySet();
            for (Map.Entry<TokenMapKey, Object> entry : entries) {
                TokenMapKey k = entry.getKey();
                Object v = entry.getValue();
                if (k.tokenClass.equals(interceptor.tokenClass()) && interceptor.appliesTo(k.action)) {
                    interceptor.postExecuteSuccessfully(k.action, returnValue, v);
                }
            }
        }
    }

    @Override
    public void postInvokeExceptionalReturn(Object token, Object proxy, Method m, Throwable exception) throws Throwable {
        Map<TokenMapKey, Object> tokenMap = (Map<TokenMapKey, Object>) token;
        for (IVaselineBusinessExecutorInterceptor interceptor : interceptors) {
            Set<Map.Entry<TokenMapKey, Object>> entries = tokenMap.entrySet();
            for (Map.Entry<TokenMapKey, Object> entry : entries) {
                TokenMapKey k = entry.getKey();
                Object v = entry.getValue();
                if (k.tokenClass.equals(interceptor.tokenClass()) && interceptor.appliesTo(k.action)) {
                    interceptor.postExecuteException(k.action, exception, v);
                }
            }
        }
    }

    private static class TokenMapKey {
        private Class<?> tokenClass;
        private IBusinessAction<?> action;

        public TokenMapKey(final Class<?> tokenClass, final IBusinessAction<?> action) {
            this.tokenClass = tokenClass;
            this.action = action;
        }

    }
}
