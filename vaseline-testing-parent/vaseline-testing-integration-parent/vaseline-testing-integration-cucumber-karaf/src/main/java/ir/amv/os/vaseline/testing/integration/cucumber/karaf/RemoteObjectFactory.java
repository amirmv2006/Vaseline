package ir.amv.os.vaseline.testing.integration.cucumber.karaf;

import cucumber.api.java.ObjectFactory;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class RemoteObjectFactory
        implements ObjectFactory {

    private static RemoteObjectFactory instance;

    private IRemoteKarafEnvironment remoteKarafEnvironment;
    private final Set<Class> stepClasses;
    private final Hashtable<Class<?>, Object> cachedProxies;

    public RemoteObjectFactory() {
        remoteKarafEnvironment = new RemoteKarafEnvironmentImpl();
        stepClasses = new HashSet<>();
        cachedProxies = new Hashtable<>();
        instance = this;
    }

    public static RemoteObjectFactory getInstance() {
        return instance;
    }

    @Override
    public void start() {
        System.out.println("RemoteObjectFactory.start");
        remoteKarafEnvironment.init();
        for (Class glueClass : stepClasses) {
            RequireClassRemotely requireClassRemotely =
                    ReflectionUtil.getAnnotationInHierarchy(glueClass, RequireClassRemotely.class);
            if (requireClassRemotely != null) {
                remoteKarafEnvironment.addExtraClasses(requireClassRemotely.value());
            }
            RegisterService registerService = ReflectionUtil.getAnnotationInHierarchy(glueClass, RegisterService.class);
            if (registerService != null) {
                remoteKarafEnvironment.addExtraClasses(registerService.interfaceClass(), registerService.implClass());
            }
            Method[] declaredMethods = glueClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                remoteKarafEnvironment.addStepDefClass(glueClass, declaredMethod);
            }
        }
    }

    @Override
    public void stop() {
        System.out.println("RemoteObjectFactory.stop");
        remoteKarafEnvironment.stopKaraf();
        cachedProxies.clear();
    }

    @Override
    public boolean addClass(Class<?> glueClass) {
        stepClasses.add(glueClass);
        return true;
    }

    @Override
    public <T> T getInstance(Class<T> glueClass) {
        return (T) cachedProxies.computeIfAbsent(glueClass, remoteKarafEnvironment::getProxiedRemoteInstance);
    }

    public IRemoteKarafEnvironment getRemoteKarafEnvironment() {
        return remoteKarafEnvironment;
    }

}
