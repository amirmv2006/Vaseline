package ir.amv.os.vaseline.security.authentication.spring.sec.api.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * Created by AMV on 2/24/2016.
 */
public interface IHttpSecurityConfigurer {

    Integer priority();
    HttpSecurity configure(HttpSecurity http) throws Exception;
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests(
            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry);
}
