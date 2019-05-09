package ir.amv.os.vaseline.testing.integration.cucumber.karaf;

import ir.amv.os.vaseline.testing.integration.cucumber.karaf.listener.IRemoteObjectListener;
import org.ops4j.pax.exam.Option;

import java.lang.reflect.Method;
import java.util.List;

public interface IRemoteKarafEnvironment {

    void init();

    void addStepDefClass(Class<?> stepDefClass, Method method);
    void addExtraClasses(Class<?>... classes);

    void startKaraf();
    void stopKaraf();

    void addOption(Option option);
    void addOptions(List<Option> options);

    <O> O getProxiedRemoteInstance(Class<O> clazz);

    List<Class<?>> getAllRemoteClasses();

    void addRemoteObjectListener(IRemoteObjectListener listener);
}
