package ir.amv.os.vaseline.business.spring.itest.advanced.search;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        extraGlue = "ir.amv.os.vaseline.business.spring.itest.shared",
        features = "classpath:features/advanced-search",
        plugin = "pretty")
public class VaselineBusinessAdvancedSearchITest {
}
