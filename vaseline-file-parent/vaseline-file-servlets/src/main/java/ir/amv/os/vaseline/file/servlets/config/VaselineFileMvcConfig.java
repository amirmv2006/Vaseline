package ir.amv.os.vaseline.file.servlets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by AMV on 2/10/2016.
 */
@Configuration
@ComponentScan("ir.amv.os.vaseline.file.servlets.server")
public class VaselineFileMvcConfig {

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        commonsMultipartResolver.setMaxUploadSize(50 * 1024 * 1024);
        return commonsMultipartResolver;
    }
}
