package ir.amv.os.vaseline.ws.rest.secured.config;

import ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.config.VaselineAuthenticationImplConfig;
import ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.config.external.IHttpSecurityConfigurer;
import ir.amv.os.vaseline.ws.rest.config.VaselineWebServiceRestConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * Created by AMV on 2/24/2016.
 */
@Configuration
@Import({
        VaselineWebServiceRestConfig.class,
        VaselineAuthenticationImplConfig.class
})
public class VaselineSecuredWebServiceRestConfig {

    @Bean
    public IHttpSecurityConfigurer restConfigurer() {
        return new IHttpSecurityConfigurer() {
            @Override
            public Integer priority() {
                return 20;
            }

            @Override
            public HttpSecurity configure(HttpSecurity http) {
                return http;
            }

            @Override
            public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
                return registry.antMatchers("/cxf/rest/**").authenticated();
            }
        };
    }
}
