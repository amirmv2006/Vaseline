package ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.config.userdetails;

import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.security.apis.authentication.model.shared.base.IBaseUserDto;
import ir.amv.os.vaseline.security.apis.authentication.service.server.base.IBaseUserService;
import ir.amv.os.vaseline.security.apis.authentication.spring.sec.config.IUserPermissionsProvider;
import ir.amv.os.vaseline.security.apis.authentication.spring.secimpl.IImplementedUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

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
