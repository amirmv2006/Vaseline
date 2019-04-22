package ir.amv.os.vaseline.ws.rest.secured.spring.osgi;

import ir.amv.os.vaseline.security.authentication.spring.sec.api.config.IHttpSecurityConfigurer;
import org.osgi.service.component.annotations.Component;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IHttpSecurityConfigurer.class
)
public class VaselineRestHttpConfigurer
        implements IHttpSecurityConfigurer {
    @Override
    public Integer priority() {
        return 10;
    }

    @Override
    public HttpSecurity configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().permitAll().and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login.do")
                //                .successHandler(authenticationSuccessRedirect())
                //				.defaultSuccessUrl("/login.success", true)
                //	.failureUrl("/login?err=1")
                //                .failureHandler(authenticationFailureRedirect())
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(((httpServletRequest, httpServletResponse, authentication) -> {

                }))
                .failureHandler(((httpServletRequest, httpServletResponse, e) -> {

                }))

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
        return http;
    }

    @Override
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
        return registry
                .antMatchers("/rest/**")
                .authenticated();
    }
}
