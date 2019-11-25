package ir.amv.os.vaseline.service.osgi.feature;

import cucumber.api.CucumberOptions;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.CucumberKarafRunner;
import net.jcip.annotations.NotThreadSafe;
import org.junit.runner.RunWith;

@RunWith(CucumberKarafRunner.class)
@CucumberOptions(
        glue = {
                "classpath:ir/amv/os/vaseline/service/osgi/feature",
                "classpath:ir/amv/os/vaseline/business/osgi/feature",
                "classpath:ir/amv/os/vaseline/data/osgi/feature",
                "classpath:ir/amv/os/vaseline/testing/integration/cucumber/karaf/stepdefs"
        })
@NotThreadSafe
public class VaselineServiceIntegrationTest {
}