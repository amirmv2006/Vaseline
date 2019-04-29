package ir.amv.os.vaseline.basics.karaf.feature;

import cucumber.api.CucumberOptions;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.CucumberKarafRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberKarafRunner.class)
@CucumberOptions(
        glue = {
                "classpath:ir/amv/os/vaseline/testing/integration/cucumber/karaf/stepdefs",
                "classpath:ir/amv/os/vaseline/basics/karaf/feature"
        })
public class VaselineBasicsIntegrationTest {
}