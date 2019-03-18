package ir.amv.os.vaseline.security.authentication.spring.sec.osgi.server;

import ir.amv.os.vaseline.security.authentication.service.api.server.base.IBaseUserService;
import ir.amv.os.vaseline.security.authentication.spring.sec.api.config.IUserPermissionsProvider;
import ir.amv.os.vaseline.security.authentication.spring.sec.def.IImplementedUserDetailsService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = UserDetailsService.class
)
public class VaselineUserDetailsService
        implements UserDetailsService, IImplementedUserDetailsService {
    private IBaseUserService baseUserService;
    private IUserPermissionsProvider userPermissionsProvider;

    @Override
    public IBaseUserService getBaseUserService() {
        return baseUserService;
    }

    @Override
    public IUserPermissionsProvider getUserPermissionsProvider() {
        return userPermissionsProvider;
    }

    @Reference
    public void setUserPermissionsProvider(final IUserPermissionsProvider userPermissionsProvider) {
        this.userPermissionsProvider = userPermissionsProvider;
    }

    @Reference
    public void setBaseUserService(final IBaseUserService baseUserService) {
        this.baseUserService = baseUserService;
    }
}
