package ir.amv.os.vaseline.basics.karaf.feature.proxy;

import cucumber.api.java.en.And;

import javax.inject.Inject;

public class ServiceLookup {

    @Inject
    private ISampleOsgiService sampleOsgiService;

    @And("I call a method from registered service")
    public void iCallAMethodFromRegisteredService() {
        sampleOsgiService.concat("Amir", "Test");
    }
}
