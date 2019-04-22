package ir.amv.os.vaseline.testing.integration.cucumber.karaf;

import cucumber.api.java.ObjectFactory;

import java.lang.reflect.Method;
import java.util.*;

public class RemoteObjectFactory
        implements ObjectFactory {

    public static IRemoteKarafEnvironment remoteKarafEnvironment;
    private final Set<Class> stepClasses;
    private final Hashtable<Class<?>, Object> cachedProxies;

    public RemoteObjectFactory() {
        remoteKarafEnvironment = new RemoteKarafEnvironmentImpl();
        stepClasses = new HashSet<>();
        cachedProxies = new Hashtable<>();
    }

    @Override
    public void start() {
        System.out.println("RemoteObjectFactory.start");
        remoteKarafEnvironment.init();
        for (Class glueClass : stepClasses) {
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
}
