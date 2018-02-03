package ir.amv.os.vaseline.ws.osgi.rest.secured;

import ir.amv.os.vaseline.security.apis.authentication.spring.sec.config.IHttpSecurityConfigurer;
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
public class VaselineRestHttpConfigurer
        implements IHttpSecurityConfigurer {
    @Override
    public Integer priority() {
        return 10;
    }

    @Override
    public HttpSecurity configure(HttpSecurity http) {
        return http;
    }

    @Override
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
        return registry
                .antMatchers("/rest/**")
                .authenticated();
    }
}
