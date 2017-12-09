package ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.server.baseapi;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.base.IBaseImplementedApi;
import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;
import ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.server.util.SpringSecurityAuthenticationUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by AMV on 2/17/2016.
 */
@Component
public class VaselineAuthenticationApiSpringSecurityImpl
        implements IBaseImplementedApi, IAuthenticationApi {
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

    @Override
    public <Proxy> Proxy getProxy(final Class<Proxy> proxyClass) {
        return null;
    }

    @Override
    public <Proxy> void setProxy(final Proxy proxy) {

    }
}