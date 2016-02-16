package ir.amv.os.vaseline.security.authentication.inapp.config;

import ir.amv.os.vaseline.security.authentication.inapp.config.permissions.IUserPermissionsProvider;
import ir.amv.os.vaseline.security.authentication.inapp.config.permissions.condition.NotExistingUserPermissionProvider;
import ir.amv.os.vaseline.security.authentication.inapp.config.permissions.impl.BasicUserPermissionsProvider;
import ir.amv.os.vaseline.security.authentication.inapp.server.model.user.IBaseUserApi;
import ir.amv.os.vaseline.security.authentication.inapp.server.model.user.impl.BaseUserApiImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by AMV on 2/16/2016.
 */
@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackages = "ir.amv.os.vaseline.security.authentication.inapp.server")
public class VaselineAuthenticationInAppConfig
        extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // This is here to ensure that the static content (JavaScript, CSS, etc)
        // is accessible from the login page without authentication
        auth.userDetailsService(userApi()).passwordEncoder(
                new ShaPasswordEncoder());
    }

    @Override
    @Bean(name =  "authenticationManager")
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

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
                .authorizeRequests()
                .antMatchers("/login.html", "/login.do","/favicon.ico", "/resources/**")
                .permitAll()
                // .antMatchers( "/index/**"
                // ).access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//				.antMatchers("/rest**")
//				.hasRole("test")
                .regexMatchers(HttpMethod.GET, "/cxf/.*\\?wsdl.*")
                .permitAll()
                .antMatchers("/index.html", "/", "/cxf/soap/**", "/cxf/rest/**")
                .authenticated()
//				.anyRequest()
//				.anonymous()
                .and()

                .csrf()
                .disable()
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
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login.do")
//                .successHandler(authenticationSuccessRedirect())
//				.defaultSuccessUrl("/login.success", true)
                //	.failureUrl("/login?err=1")
//                .failureHandler(authenticationFailureRedirect())
                .usernameParameter("username")
                .passwordParameter("password")

                .and()

                // This is where the logout page and process is configured. The
                // logout-url is the URL to send
                // the user to in order to logout, the logout-success-url is
                // where they are taken if the logout
                // is successful, and the delete-cookies and invalidate-session
                // make sure that we clean up after logout
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login.html")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);


        // The session management is used to ensure the user only has
        // one session. This isn't
        // compulsory but can add some extra security to your
        // application.
//				.sessionManagement().invalidSessionUrl("/login?time=1")
//				.maximumSessions(1)
        ;
    }

     @Bean
     @Conditional(NotExistingUserPermissionProvider.class)
     public IUserPermissionsProvider userPermissionsProvider() {
     return new BasicUserPermissionsProvider();
     }

    @Bean
    public IBaseUserApi userApi() {
        return new BaseUserApiImpl();
    }

}
