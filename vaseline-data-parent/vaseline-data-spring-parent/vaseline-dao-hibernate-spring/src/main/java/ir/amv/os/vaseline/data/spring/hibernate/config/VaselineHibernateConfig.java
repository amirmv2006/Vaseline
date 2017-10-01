package ir.amv.os.vaseline.data.spring.hibernate.config;

import ir.amv.os.vaseline.data.spring.hibernate.server.dozer.fieldmapper.HibernateLazyFieldMapper;
import ir.amv.os.vaseline.data.spring.jdbc.config.VaselineJdbcConfig;
import ir.amv.os.vaseline.basics.spring.caching.config.VaselineCachingConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by AMV on 2/10/2016.
 */
@Configuration
@EnableTransactionManagement
@Import( {
        VaselineJdbcConfig.class,
        VaselineCachingConfig.class
})
public class VaselineHibernateConfig {

    @Autowired
    Environment environment;

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean em = new LocalSessionFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("ir.amv");
        em.setHibernateProperties(additionalProperties());
        try {
            em.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return em.getObject();
    }

    @Bean
    @Autowired
    @Order(1)
    public PlatformTransactionManager transactionManager(DataSource dataSource,
                                                         SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    @Autowired
    public TransactionTemplate transactionTemplate(
            PlatformTransactionManager transactionManager) {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager);
        return transactionTemplate;
    }



    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.validator.apply_to_ddl", "false");
        properties.put("hibernate.validator.autoregister_listeners", "false");
        properties.put("hibernate.dialect",
                environment.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",
                environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.generate_statistics", "true");
        properties.put("hibernate.use_sql_comments", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.connection.autocommit", "false");
        if (environment.acceptsProfiles("initDB")) {
            properties.put("hibernate.hbm2ddl.auto","create-drop");
        } else {
            properties.put("hibernate.hbm2ddl.auto","update");
        }

        properties.put("hibernate.cache.use_second_level_cache", "true");
        properties.put("hibernate.cache.use_query_cache", "true");
        properties.put("hibernate.cache.region.factory_class",
                "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        return properties;
    }

    @Bean
    public HibernateLazyFieldMapper hibernateLazyFieldMapper() {
        return new HibernateLazyFieldMapper();
    }
}
