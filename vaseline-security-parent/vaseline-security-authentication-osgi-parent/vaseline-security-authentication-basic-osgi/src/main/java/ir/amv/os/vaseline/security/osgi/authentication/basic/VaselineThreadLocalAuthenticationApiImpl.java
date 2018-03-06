package ir.amv.os.vaseline.security.osgi.authentication.basic;

import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;
import ir.amv.os.vaseline.security.apis.authentication.basicimpl.IImplementedThreadLocalAuthenticationApi;
import ir.amv.os.vaseline.security.apis.authentication.basicimpl.ISetAuthenticationApi;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IAuthenticationApi.class,
                ISetAuthenticationApi.class
        }
)
public class VaselineThreadLocalAuthenticationApiImpl
        implements IImplementedThreadLocalAuthenticationApi, IAuthenticationApi {

    private ThreadLocal<String> currentUsernameThreadLocal = new InheritableThreadLocal<>();

    @Override
    public ThreadLocal<String> getCurrentUsernameThreadLocal() {
        return currentUsernameThreadLocal;
    }

}
