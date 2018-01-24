package ir.amv.os.vaseline.security.spring.auditimpl.log.spring.config;

import ir.amv.os.vaseline.business.spring.executor.config.VaselineBusinessExecutorConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Amir
 */
@Configuration
@Import(VaselineBusinessExecutorConfig.class)
@ComponentScan("ir.amv.os.vaseline.security.spring.auditimpl.log.spring.server")
public class VaselineAuditLogConfig {
}
