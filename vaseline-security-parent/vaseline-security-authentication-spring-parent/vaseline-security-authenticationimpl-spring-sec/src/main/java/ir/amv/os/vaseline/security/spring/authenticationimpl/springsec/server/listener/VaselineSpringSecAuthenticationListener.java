package ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.server.listener;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.security.authentication.business.api.server.base.IBaseUserApi;
import ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.server.util.SpringSecurityAuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author Amir
 */
@Component
public class VaselineSpringSecAuthenticationListener
        implements ApplicationListener<AbstractAuthenticationEvent> {

    private IBaseUserApi<?> baseUserApi;

    @Override
    public void onApplicationEvent(final AbstractAuthenticationEvent event) {
        Authentication authentication = event.getAuthentication();
        String username = SpringSecurityAuthenticationUtil.getUsernameFromAuthentication(authentication);
        try {
            if (event instanceof InteractiveAuthenticationSuccessEvent) {
                baseUserApi.authenticationSuccessful(username);
            } else if (event instanceof AuthenticationSuccessEvent) {
                baseUserApi.authenticationSuccessful(username);
            } else if (event instanceof AbstractAuthenticationFailureEvent) {
                baseUserApi.authenticationFailure(username);
            }
        } catch (BaseVaselineServerException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setBaseUserApi(final IBaseUserApi<?> baseUserApi) {
        this.baseUserApi = baseUserApi;
    }
}
