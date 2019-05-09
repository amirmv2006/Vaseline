package ir.amv.os.vaseline.basics.karaf.feature.proxy;

import cucumber.api.java.en.And;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RequireClassRemotely;

import javax.inject.Inject;

@RequireClassRemotely(ISampleOsgiService.class)
public class ServiceLookup {

    @Inject
    private ISampleOsgiService sampleOsgiService;

    @And("I call a method from registered service")
    public void iCallAMethodFromRegisteredService() {
        sampleOsgiService.concat("Amir", "Test");
    }
}
