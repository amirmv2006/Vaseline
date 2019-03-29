package ir.amv.os.vaseline.basics.karaf.feature;

import cucumber.api.junit.Cucumber;
import cucumber.runtime.Env;
import org.junit.runners.model.InitializationError;

public class MyRunner extends Cucumber {
    static {
        Env.customObjectFactory = AmirObjectFactory.class;
    }
    public MyRunner(Class clazz) throws InitializationError {
        super(clazz);
    }
}
