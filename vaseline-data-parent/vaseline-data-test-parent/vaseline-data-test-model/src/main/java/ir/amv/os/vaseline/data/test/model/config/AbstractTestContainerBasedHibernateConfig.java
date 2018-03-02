package ir.amv.os.vaseline.data.test.model.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.testcontainers.containers.JdbcDatabaseContainer;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Amir
 */
public abstract class AbstractTestContainerBasedHibernateConfig<P extends JdbcDatabaseContainer> {

    protected abstract P createContainer();
    protected abstract Class<? extends Dialect> getDialectClass();

    @Bean
    public JdbcDatabaseContainer<?> jdbcDatabaseContainer() {
        P container = createContainer();
        container.start();
        return container;
    }

    @Bean
    public DataSource dataSource(JdbcDatabaseContainer<?> jdbcDatabaseContainer) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(jdbcDatabaseContainer.getJdbcUrl());
        dataSource.setUsername(jdbcDatabaseContainer.getUsername());
        dataSource.setPassword(jdbcDatabaseContainer.getPassword());
        return dataSource;
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
                getDialectClass().getName());
        properties.put("hibernate.show_sql",
                true);
        properties.put("hibernate.generate_statistics", "false");
        properties.put("hibernate.use_sql_comments", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.connection.autocommit", "false");
        properties.put("hibernate.hbm2ddl.auto","create-drop");

        properties.put("hibernate.cache.use_second_level_cache", "false");
        properties.put("hibernate.cache.use_query_cache", "false");
        return properties;
    }

}
