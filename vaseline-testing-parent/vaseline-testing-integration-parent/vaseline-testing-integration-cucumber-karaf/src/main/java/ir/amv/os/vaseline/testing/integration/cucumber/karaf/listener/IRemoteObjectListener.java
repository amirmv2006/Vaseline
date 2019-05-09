package ir.amv.os.vaseline.testing.integration.cucumber.karaf.listener;

import java.lang.reflect.Method;

public interface IRemoteObjectListener {
    <C> void remoteObjectCreated(Class<C> objectClass, C object);

    void remoteMethodInvoked(Object proxy, Method method, Object[] args);
}
