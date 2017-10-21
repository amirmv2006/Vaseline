package ir.amv.os.vaseline.data.test.model.config;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCityEntity;
import ir.amv.os.vaseline.data.test.model.server.entity.TestContinentEntity;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.server.entity.TestStateEntity;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestCityRepository;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestContinentRepository;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestCountryRepository;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestStateRepository;
import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Amir
 */
@Configuration
public class TestDataModelHibernateConfig {

    @Autowired
    Environment environment;

    @Bean
    public ITestContinentRepository testContinentRepository(SessionFactory sessionFactory) {
        return new ITestContinentRepository() {
            @Override
            public TestContinentEntity save(TestContinentEntity testContinentEntity) {
                sessionFactory.getCurrentSession().save(testContinentEntity);
                return testContinentEntity;
            }

            @Override
            public void delete(TestContinentEntity testContinentEntity) {
                sessionFactory.getCurrentSession().delete(testContinentEntity);
            }
        };
    }

    @Bean
    public ITestCountryRepository testCountryRepository(SessionFactory sessionFactory) {
        return new ITestCountryRepository() {
            @Override
            public TestCountryEntity save(TestCountryEntity testCountryEntity) {
                sessionFactory.getCurrentSession().save(testCountryEntity);
                return testCountryEntity;
            }

            @Override
            public void delete(TestCountryEntity testCountryEntity) {
                sessionFactory.getCurrentSession().delete(testCountryEntity);
            }
        };
    }

    @Bean
    public ITestStateRepository testStateRepository(SessionFactory sessionFactory) {
        return new ITestStateRepository() {
            @Override
            public TestStateEntity save(TestStateEntity testStateEntity) {
                sessionFactory.getCurrentSession().save(testStateEntity);
                return testStateEntity;
            }

            @Override
            public void delete(TestStateEntity testStateEntity) {
                sessionFactory.getCurrentSession().delete(testStateEntity);
            }
        };
    }

    @Bean
    public ITestCityRepository testCityRepository(SessionFactory sessionFactory) {
        return new ITestCityRepository() {
            @Override
            public TestCityEntity save(TestCityEntity testCityEntity) {
                sessionFactory.getCurrentSession().save(testCityEntity);
                return testCityEntity;
            }

            @Override
            public void delete(TestCityEntity testCityEntity) {
                sessionFactory.getCurrentSession().delete(testCityEntity);
            }
        };
    }

    @Bean
    public DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl(environment.getProperty("jdbc.url"));
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
                environment.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",
                environment.getProperty("hibernate.show_sql"));
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
