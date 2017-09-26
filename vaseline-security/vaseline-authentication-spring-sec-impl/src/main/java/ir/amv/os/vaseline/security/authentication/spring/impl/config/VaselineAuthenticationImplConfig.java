package ir.amv.os.vaseline.security.authentication.spring.impl.config;

import ir.amv.os.vaseline.security.authentication.spring.impl.config.external.IHttpSecurityConfigurer;
import ir.amv.os.vaseline.security.authentication.spring.impl.config.permissions.IUserPermissionsProvider;
import ir.amv.os.vaseline.security.authentication.spring.impl.config.permissions.condition.NotExistingUserPermissionProvider;
import ir.amv.os.vaseline.security.authentication.spring.impl.config.permissions.impl.BasicUserPermissionsProvider;
import ir.amv.os.vaseline.security.authentication.spring.impl.config.userdetailsservice.VaselineUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by AMV on 2/16/2016.
 */
@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackages = "ir.amv.os.vaseline.security.authentication.spring.impl.server")
public class VaselineAuthenticationImplConfig
        extends WebSecurityConfigurerAdapter {

    private List<IHttpSecurityConfigurer> securityConfigurers;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // This is here to ensure that the static content (JavaScript, CSS, etc)
        // is accessible from the login page without authentication
        auth.userDetailsService(userDetailsService()).passwordEncoder(
                new ShaPasswordEncoder());
    }

    @Override
    @Bean(name = "authenticationManager")
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http

                // access-denied-page: this is the page users will be
                // redirected to when they try to access protected areas.
                .exceptionHandling()
//				.accessDeniedPage("/403")
                .and()

                // The intercept-url configuration is where we specify what
                // roles are allowed access to what areas.
                // We specifically force the connection to https for all the
                // pages, although it could be sufficient
                // just on the login page. The access parameter is where the
                // expressions are used to control which
                // roles can access specific areas. One of the most important
                // things is the order of the intercept-urls,
                // the most catch-all type patterns should at the bottom of the
                // list as the matches are executed
                // in the order they are configured below. So /** (anyRequest())
                // should always be at the bottom of the list.
                .authorizeRequests();
        for (IHttpSecurityConfigurer securityConfigurer : securityConfigurers) {
            registry = securityConfigurer.authorizeRequests(registry);
        }

//                .regexMatchers(HttpMethod.GET, "/cxf/.*\\?wsdl.*")
//                .permitAll()
//                .antMatchers("/index.html", "/", "/cxf/soap/**", "/cxf/rest/**")
//                .authenticated()
//				.anyRequest()
//				.anonymous()
        http = registry.and()

                .csrf()
                .disable();
        // This is where we configure our login form.
        // login-page: the page that contains the login screen
        // login-processing-url: this is the URL to which the login form
        // should be submitted
        // default-target-url: the URL to which the user will be
        // redirected if they login successfully
        // authentication-failure-url: the URL to which the user will be
        // redirected if they fail login
        // username-parameter: the name of the request parameter which
        // contains the username
        // password-parameter: the name of the request parameter which
        // contains the password
        for (IHttpSecurityConfigurer securityConfigurer : securityConfigurers) {
            http = securityConfigurer.configure(http);
        }

        // The session management is used to ensure the user only has
        // one session. This isn't
        // compulsory but can add some extra security to your
        // application.
//				.sessionManagement().invalidSessionUrl("/login?time=1")
//				.maximumSessions(1)
        ;
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new VaselineUserDetailsService();
    }

    @Bean
    @Conditional(NotExistingUserPermissionProvider.class)
    public IUserPermissionsProvider userPermissionsProvider() {
        return new BasicUserPermissionsProvider();
    }

    @Autowired
    public void setSecurityConfigurers(List<IHttpSecurityConfigurer> securityConfigurers) {
        this.securityConfigurers = securityConfigurers;
        Collections.sort(securityConfigurers, new Comparator<IHttpSecurityConfigurer>() {
            @Override
            public int compare(IHttpSecurityConfigurer o1, IHttpSecurityConfigurer o2) {
                return o2.priority().compareTo(o1.priority()); // sort descending
            }
        });
    }
}
