package ir.amv.os.vaseline.security.osgi.authentication.spring.sec.config;

import ir.amv.os.vaseline.security.apis.authentication.spring.sec.config.IHttpSecurityConfigurer;
import ir.amv.os.vaseline.security.apis.authentication.spring.sec.config.IUserPermissionsProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Amir
 */
public class SpringOsgiBridge {

    private List<IHttpSecurityConfigurer> securityConfigurerList;
    private IUserPermissionsProvider userPermissionsProvider;
    private UserDetailsService userDetailsService;

    private static SpringOsgiBridge instance;

    public static synchronized SpringOsgiBridge getInstance() {
        if (instance == null) {
            instance = new SpringOsgiBridge();
        }
        return instance;
    }

    private SpringOsgiBridge() {
    }

    public List<IHttpSecurityConfigurer> getSecurityConfigurerList() {
        return securityConfigurerList;
    }

    public void setSecurityConfigurerList(final List<IHttpSecurityConfigurer> securityConfigurerList) {
        this.securityConfigurerList = securityConfigurerList;
    }

    public IUserPermissionsProvider getUserPermissionsProvider() {
        return userPermissionsProvider;
    }

    public void setUserPermissionsProvider(final IUserPermissionsProvider userPermissionsProvider) {
        this.userPermissionsProvider = userPermissionsProvider;
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
