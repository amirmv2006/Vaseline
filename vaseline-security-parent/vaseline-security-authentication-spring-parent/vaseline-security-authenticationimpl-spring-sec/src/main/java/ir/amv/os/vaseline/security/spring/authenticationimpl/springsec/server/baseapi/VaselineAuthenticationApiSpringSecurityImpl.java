package ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.server.baseapi;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.security.authentication.basic.api.server.IAuthenticationApi;
import ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.server.util.SpringSecurityAuthenticationUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by AMV on 2/17/2016.
 */
@Component
public class VaselineAuthenticationApiSpringSecurityImpl
        implements IAuthenticationApi {

    @Override
    public String getCurrentUsername() throws BaseVaselineServerException {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String username = SpringSecurityAuthenticationUtil.getUsernameFromAuthentication(authentication);
        if (username != null) {
            return username;
        }
        throw new BaseVaselineServerException("current user is null.");
    }

}
