package ir.amv.os.vaseline.testing.integration.cucumber.karaf.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RemoteObjectFactory;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.SetupKaraf;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import javax.inject.Inject;
import java.time.Duration;
import java.time.Instant;

import static ir.amv.os.vaseline.testing.integration.cucumber.karaf.helper.KarafOptionsHelper.*;

public class KarafSetupStepDefs {

    private Bundle bundle;

    public KarafSetupStepDefs() {
        System.out.println("Created");
    }

    @Given("^I have karaf$")
    @SetupKaraf
    public void iHaveKaraf() {
        RemoteObjectFactory.remoteKarafEnvironment.addOptions(defaultOptions());
    }

    @Given("^start bundle with groupId=\"([^\"]*)\" and artifactId=\"([^\"]*)\" and version as in project$")
    @SetupKaraf
    public void mavenBundleWithVersionAsInProject(String groupId, String artifactId) {
        RemoteObjectFactory.remoteKarafEnvironment.addOption(
                mavenBundleVersionAsInProject(groupId, artifactId)
        );
    }

    @Given("^feature with groupId=\"([^\"]*)\", artifactId=\"([^\"]*)\" and name=\"([^\"]*)\" is deployed")
    @SetupKaraf
    public void installFeature(String featureGroupId, String featureArtifactId, String featureNames) {
        RemoteObjectFactory.remoteKarafEnvironment.addOption(
                deployFeature(featureGroupId, featureArtifactId, featureNames)
        );
    }

    @When("^start karaf$")
    @SetupKaraf
    public void runKaraf() {
        RemoteObjectFactory.remoteKarafEnvironment.startKaraf();
    }

    @Inject
    BundleContext bundleContext;

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
