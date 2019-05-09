package ir.amv.os.vaseline.business.osgi.feature;

import cucumber.api.CucumberOptions;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.CucumberKarafRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberKarafRunner.class)
@CucumberOptions(
        glue = {
                "classpath:ir/amv/os/vaseline/testing/integration/cucumber/karaf/stepdefs",
                "classpath:ir/amv/os/vaseline/data/osgi/feature",
                "classpath:ir/amv/os/vaseline/business/osgi/feature"
        })
public class VaselineBusinessIntegrationTest {
}