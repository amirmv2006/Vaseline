package ir.amv.os.vaseline.base.i18n.config;

import ir.amv.os.vaseline.base.i18n.server.base.IVaselineI18nFileProvider;
import ir.amv.os.vaseline.base.i18n.server.base.impl.VaselineClasspathI18nFileProviderImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.List;

/**
 * Created by AMV on 2/10/2016.
 */
@Configuration
public class VaselineI18nConfig {

    @Bean
    public MessageSource messageSource(List<IVaselineI18nFileProvider> fileProviders) {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setFallbackToSystemLocale(false);
        String[] basenames = new String[fileProviders.size()];
        for (int i = 0; i < fileProviders.size(); i++) {
            basenames[i] = fileProviders.get(i).fileBaseName();
        }
        messageSource.setBasenames(basenames);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public IVaselineI18nFileProvider defaultI18nFileProvider() {
        return new VaselineClasspathI18nFileProviderImpl("vaseline-default-i18n");
    }
}
