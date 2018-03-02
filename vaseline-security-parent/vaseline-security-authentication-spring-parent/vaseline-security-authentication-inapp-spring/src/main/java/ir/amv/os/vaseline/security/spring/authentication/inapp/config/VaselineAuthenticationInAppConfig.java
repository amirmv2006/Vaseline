package ir.amv.os.vaseline.security.spring.authentication.inapp.config;

import ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.config.VaselineAuthenticationImplConfig;
import ir.amv.os.vaseline.security.apis.authentication.spring.sec.config.IHttpSecurityConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by AMV on 2/24/2016.
 */
@Configuration
@Import({
        VaselineAuthenticationImplConfig.class
})
public class VaselineAuthenticationInAppConfig {

    @Bean
    public IHttpSecurityConfigurer inAppConfigurer() {
        return new IHttpSecurityConfigurer() {
            @Override
            public Integer priority() {
                return 10;
            }

            @Override
            public HttpSecurity configure(HttpSecurity http) {
                try {
                    http.formLogin()
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
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
                return registry;
            }
        };
    }
}
