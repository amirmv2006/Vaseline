package ir.amv.os.vaseline.testing.integration.cucumber.karaf;

import cucumber.api.junit.Cucumber;
import cucumber.runtime.Env;
import org.junit.runners.model.InitializationError;

public class CucumberKarafRunner extends Cucumber {
    static {
        Env.customObjectFactory = RemoteObjectFactory.class;
    }

    public CucumberKarafRunner(Class clazz) throws InitializationError {
        super(clazz);
    }
}
