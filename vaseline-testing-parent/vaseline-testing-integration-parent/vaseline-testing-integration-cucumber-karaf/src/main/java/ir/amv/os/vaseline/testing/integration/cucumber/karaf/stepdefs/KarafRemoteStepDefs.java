package ir.amv.os.vaseline.testing.integration.cucumber.karaf.stepdefs;

import cucumber.api.java.en.Then;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RegisterService;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RegisterServiceProperty;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.ops4j.pax.swissbox.tracker.ServiceLookupException;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class KarafRemoteStepDefs {

    private Bundle bundle;

    public KarafRemoteStepDefs() {
        System.out.println("Created");
    }

    @Inject
    BundleContext bundleContext;


    public void registerServiceFor(String classFqn) throws Exception {
        Class<?> aClass = this.getClass().getClassLoader().loadClass(classFqn);
        RegisterService registerService = ReflectionUtil.getAnnotationInHierarchy(aClass, RegisterService.class);
        assert registerService != null;
        Class<?> implClass = registerService.implClass();
        Class<?> interfaceClass = registerService.interfaceClass();
        RegisterServiceProperty[] props = registerService.properties();
        Constructor<?> constructor = implClass.getConstructors()[0];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        List<Object> params = new ArrayList<>();
        for (Class<?> parameterType : parameterTypes) {
            String dependencyClassName = parameterType.getName();
            ServiceTracker<Object, Object> tracker = new ServiceTracker<>(bundleContext, dependencyClassName, null);
            tracker.open();
            Object dependency = tracker.waitForService(10_000);
            if (dependency == null) {
                throw new ServiceLookupException("gave up waiting for service " + dependencyClassName);
            }
            params.add(dependency);
        }
        Object serviceInstance = constructor.newInstance(params.toArray());
        Hashtable<String, Object> properties = new Hashtable<>();
        for (RegisterServiceProperty prop : props) {
            Object value;
            switch (prop.propertyType()) {
                case "BOOLEAN":
                    value = prop.boolValue();
                    break;
                case "INTEGER":
                    value = prop.intValue();
                    break;
                default:
                    value = prop.strValue();
            }
            properties.put(prop.propertyName(), value);
        }
        bundleContext.registerService(interfaceClass.getName(), serviceInstance, properties);
    }

    @Then("^bundle \"([^\"]*)\" is started$")
    public void bundleStarted(String bundleSymbolicName) throws InterruptedException {
        Instant startTime = Instant.now();
        boolean bundleStarted = false;
        while (Duration.between(startTime, Instant.now()).toMillis() < 60_000) { // 1 minutes
            Bundle[] bundles = bundleContext.getBundles();
            for (Bundle bundle : bundles) {
                if (bundle.getSymbolicName().toLowerCase().contains(bundleSymbolicName)) {
                    this.bundle = bundle;
                    bundleStarted = true;
                }
            }
            if (bundleStarted) {
                break;
            }
            Thread.sleep(1000L);
        }
        if (!bundleStarted) {
            throw new IllegalStateException("Bundle not started " + bundleSymbolicName);
        }
    }

    @Then("^bundle \"([^\"]*)\" is not deployed")
    public void bundleNotDeployed(String bundleSymbolicName) throws InterruptedException {
        Bundle[] bundles = bundleContext.getBundles();
        for (Bundle bundle : bundles) {
            if (bundle.getSymbolicName().toLowerCase().contains(bundleSymbolicName)) {
                throw new IllegalStateException("Bundle has been deployed: " + bundleSymbolicName);
            }
        }
    }

    @Then("^Service of type \"([^\"]*)\" is registered$")
    public void serviceStarted(String serviceFqn) {
        boolean found = false;
        ServiceReference<?>[] registeredServices = bundle.getRegisteredServices();
        for (ServiceReference<?> registeredService : registeredServices) {
            String[] objectClasses = (String[]) registeredService.getProperty("objectClass");
            for (String objectClass : objectClasses) {
                if (serviceFqn.equals(objectClass)) {
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            throw new IllegalStateException("Can not find service " + serviceFqn);
        }
    }

    @Then("^Service of type \"([^\"]*)\" is registered in (\\d*) seconds$")
    public void serviceStartedWithTimeout(String serviceFqn, Integer timeout) throws InterruptedException {
        Instant start = Instant.now();
        boolean found = false;
        while (Duration.between(start, Instant.now()).toMillis() < timeout * 1000) {
            ServiceReference<?>[] registeredServices = bundle.getRegisteredServices();
            for (ServiceReference<?> registeredService : registeredServices) {
                String[] objectClasses = (String[]) registeredService.getProperty("objectClass");
                for (String objectClass : objectClasses) {
                    if (serviceFqn.equals(objectClass)) {
                        found = true;
                        break;
                    }
                }
            }
            Thread.sleep(1000);
        }
        if (!found) {
            throw new IllegalStateException("Can not find service " + serviceFqn);
        }
    }
}
