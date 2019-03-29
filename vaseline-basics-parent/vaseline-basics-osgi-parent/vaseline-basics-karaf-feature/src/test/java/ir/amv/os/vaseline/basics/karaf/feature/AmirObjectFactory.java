package ir.amv.os.vaseline.basics.karaf.feature;

import cucumber.api.java.ObjectFactory;

public class AmirObjectFactory implements ObjectFactory {
    @Override
    public void start() {
        System.out.println("Started");
    }

    @Override
    public void stop() {
        System.out.println("Stopped");
    }

    @Override
    public boolean addClass(Class<?> glueClass) {
        System.out.println("glueClass = " + glueClass);
        return true;
    }

    @Override
    public <T> T getInstance(Class<T> glueClass) {
        System.out.println("glueClass = " + glueClass);
        return null;
    }
}
