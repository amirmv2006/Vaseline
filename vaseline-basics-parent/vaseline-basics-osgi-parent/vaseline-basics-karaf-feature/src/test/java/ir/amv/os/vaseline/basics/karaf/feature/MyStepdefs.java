package ir.amv.os.vaseline.basics.karaf.feature;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.PaxCucumber;
import org.junit.runner.RunWith;
import org.osgi.framework.BundleContext;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@RunWith(PaxCucumber.class)
public class MyStepdefs {

    @Inject
    BundleContext bundleContext;

    @Given("I have karaf")
    public void iHaveKaraf() {

    }


    @When("run karaf")
    public void runKaraf() {
    }

    @Then("karaf is started")
    public void karafIsStarted() {
        assertNotNull(bundleContext);
    }
}
