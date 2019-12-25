package ir.amv.os.vaseline.data.jpa.spring.itest.basic;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/basic", plugin = "pretty")
public class VaselineDataBasicITest {
}
