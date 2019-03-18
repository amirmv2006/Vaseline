package ir.amv.os.vaseline.security.authentication.spring.sec.osgi.server;

import ir.amv.os.vaseline.security.authentication.spring.sec.api.config.IHttpSecurityConfigurer;
import org.osgi.service.component.annotations.Component;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IHttpSecurityConfigurer.class
)
public class VaselineHttpSecurityConfigurerImpl
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
                .antMatchers("/noauth/**")
                .permitAll();
    }
}
