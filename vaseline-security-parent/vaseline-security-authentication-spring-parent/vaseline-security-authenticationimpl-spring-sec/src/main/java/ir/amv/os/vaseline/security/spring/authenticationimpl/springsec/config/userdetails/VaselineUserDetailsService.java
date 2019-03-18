package ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.config.userdetails;

import ir.amv.os.vaseline.security.authentication.service.api.server.base.IBaseUserService;
import ir.amv.os.vaseline.security.authentication.spring.sec.api.config.IUserPermissionsProvider;
import ir.amv.os.vaseline.security.authentication.spring.sec.def.IImplementedUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by AMV on 2/28/2016.
 */
public class VaselineUserDetailsService
        implements IImplementedUserDetailsService {

    @Autowired
    IBaseUserService baseUserService;

    @Autowired
    IUserPermissionsProvider userPermissionsProvider;

    @Override
    public IBaseUserService getBaseUserService() {
        return baseUserService;
    }

    @Override
    public IUserPermissionsProvider getUserPermissionsProvider() {
        return userPermissionsProvider;
    }

}
