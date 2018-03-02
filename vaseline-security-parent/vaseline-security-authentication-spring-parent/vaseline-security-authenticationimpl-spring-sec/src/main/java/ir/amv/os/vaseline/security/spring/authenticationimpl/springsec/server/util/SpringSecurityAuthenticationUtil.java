package ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.server.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Amir
 */
public class SpringSecurityAuthenticationUtil {

    public static String getUsernameFromAuthentication(Authentication authentication) {
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
        return null;
    }
}
