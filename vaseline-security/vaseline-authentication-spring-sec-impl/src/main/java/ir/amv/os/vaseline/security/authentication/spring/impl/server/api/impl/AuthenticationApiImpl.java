package ir.amv.os.vaseline.security.authentication.spring.impl.server.api.impl;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.api.BaseApiImpl;
import ir.amv.os.vaseline.base.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.security.authentication.api.shared.api.IAuthenticationApi;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by AMV on 2/17/2016.
 */
@Component
public class AuthenticationApiImpl
        extends BaseApiImpl
        implements IAuthenticationApi {
    @Override
    public String getCurrentUsername() throws BaseVaselineServerException {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails ud = (UserDetails) principal;
                return ud.getUsername();
            } else if (principal instanceof String) {
                String username = (String) principal;
                return username;
            }
        }
        throw new BaseVaselineServerException("current user is null.");
    }
}
