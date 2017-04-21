package ir.amv.os.vaseline.angular.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by AMV on 4/21/2017.
 */
@Configuration
public class VaselineAngularBaseMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/vaseline/angular-base/vendor/**").addResourceLocations("classpath:/vendor/");
        registry.addResourceHandler("/vaseline/angular-base/common/**").addResourceLocations("classpath:/common/");
    }
}
