package ir.amv.os.vaseline.basics.osgi.logging.slf4j.activator;

import ir.amv.os.vaseline.basics.logging.api.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.osgi.logging.slf4j.server.categorizer.DefaultVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.logging.slf4j.osgi.VaselineLoggerSlf4jImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amir
 */
@Configuration
public class VaselineLoggingSlf4jConfig {

    @Bean
    @ConditionalOnMissingBean(IVaselineLogCategorizer.class)
    public IVaselineLogCategorizer defaultVaselineLogCategorizer() {
        return new DefaultVaselineLogCategorizer();
    }

    @Bean
    @ConditionalOnMissingBean(IVaselineLogger.class)
    public IVaselineLogger slf4jVaselineLogger() {
        return new VaselineLoggerSlf4jImpl();
    }
}
