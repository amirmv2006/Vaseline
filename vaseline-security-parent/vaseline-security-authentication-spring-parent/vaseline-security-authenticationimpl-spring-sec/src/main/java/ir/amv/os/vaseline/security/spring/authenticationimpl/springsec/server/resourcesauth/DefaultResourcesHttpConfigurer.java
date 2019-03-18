package ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.server.resourcesauth;

import ir.amv.os.vaseline.security.authentication.spring.sec.api.config.IHttpSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * Created by AMV on 2/24/2016.
 */
@Component
public class DefaultResourcesHttpConfigurer
        implements IHttpSecurityConfigurer {

    @Override
    public Integer priority() {
        return 0;
    }

    @Override
    public HttpSecurity configure(HttpSecurity http) {
        return http;
    }

    @Override
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
        return registry
                .antMatchers("/login.html", "/login.do", "/favicon.ico", "/resources/**")
                .permitAll()
                .antMatchers("/index.html")
                .authenticated();
    }
}
