package ir.amv.os.vaseline.testing.integration.cucumber.karaf.stepdefs;

import cucumber.api.java.en.Then;
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


    public void registerService(String implClassName, String interfaceClassName) throws Exception {
        Class<?> implClass = this.getClass().getClassLoader().loadClass(implClassName);
        Class<?> interfaceClass = this.getClass().getClassLoader().loadClass(interfaceClassName);
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
