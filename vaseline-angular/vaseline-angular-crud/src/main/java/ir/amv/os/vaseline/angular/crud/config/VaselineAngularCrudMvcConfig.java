package ir.amv.os.vaseline.angular.crud.config;

import ir.amv.os.vaseline.angular.base.config.VaselineAngularBaseMvcConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by AMV on 4/21/2017.
 */
@Configuration
@Import(VaselineAngularBaseMvcConfig.class)
public class VaselineAngularCrudMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/vaseline/angular-base/crud/**").addResourceLocations("classpath:/crud/");
    }
}
