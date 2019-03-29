package ir.amv.os.vaseline.basics.karaf.feature;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.runtime.Env;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.PaxCucumber;
import ir.amv.os.vaseline.thirdparty.it.karaf.helper.AbstractVaselineKarafIntegrationTest;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

/**
 * An acceptance test which uses PaxExam in-container testing
 * 
 * Cucumber.Options documentation at:
 * 		http://cukes.info/api/cucumber/jvm/javadoc/cucumber/api/junit/Cucumber.Options.html
 * 		http://emprovisetech.blogspot.com/2012/11/acceptance-testing-cucumber-jvm.html
 * 
 */
@RunWith(MyRunner.class)
@ExamReactorStrategy(PerClass.class)
@CucumberOptions(	features = {"src/test/features"},
		glue = {"classpath:"})
public class RunCucumberTests extends AbstractVaselineKarafIntegrationTest {
	@Override
	protected String[] getTestFeatures() {
		return new String[]{
				"vaseline-logger-slf4j",
				"vaseline-basics-logging-common"
		};
	}
}