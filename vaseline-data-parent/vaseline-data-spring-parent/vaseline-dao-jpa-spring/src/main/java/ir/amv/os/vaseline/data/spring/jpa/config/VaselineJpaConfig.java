package ir.amv.os.vaseline.data.spring.jpa.config;

import ir.amv.os.vaseline.basics.spring.cache.config.VaselineCachingConfig;
import ir.amv.os.vaseline.data.spring.jdbc.config.VaselineJdbcConfig;
import ir.amv.os.vaseline.data.spring.jpa.server.dozer.fieldmapper.JpaLazyFieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by AMV on 2/10/2016.
 */
@Configuration
@EnableTransactionManagement
@Import( {
        VaselineJdbcConfig.class,
        VaselineCachingConfig.class
})
public class VaselineJpaConfig {

    @Autowired
    Environment environment;

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    @Autowired
    public TransactionTemplate transactionTemplate(
            PlatformTransactionManager transactionManager) {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager);
        return transactionTemplate;
    }



    @Bean
    public JpaLazyFieldMapper hibernateLazyFieldMapper() {
        return new JpaLazyFieldMapper();
    }
}
