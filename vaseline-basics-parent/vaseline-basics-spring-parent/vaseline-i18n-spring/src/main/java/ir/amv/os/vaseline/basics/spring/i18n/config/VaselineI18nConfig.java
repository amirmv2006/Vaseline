package ir.amv.os.vaseline.basics.spring.i18n.config;

import ir.amv.os.vaseline.basics.i18n.api.server.exc.NoSuchMessageI18nException;
import ir.amv.os.vaseline.basics.i18n.api.server.file.resolver.IVaselineI18nFileProvider;
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.IVaselineMessageTranslator;
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.defimpl.BaseVaselineMessageTranslatorImpl;
import ir.amv.os.vaseline.basics.spring.i18n.server.base.impl.VaselineClasspathI18nFileProviderImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.List;
import java.util.Locale;

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

    @Bean
    @ConditionalOnMissingBean(IVaselineMessageTranslator.class)
    public IVaselineMessageTranslator messageTranslator(final MessageSource messageSource) {
        return new BaseVaselineMessageTranslatorImpl() {
            @Override
            public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageI18nException {
                try {
                    return messageSource.getMessage(code, args, locale);
                } catch (NoSuchMessageException e) {
                    throw new NoSuchMessageI18nException(e.getMessage());
                }
            }
        };
    }
}
