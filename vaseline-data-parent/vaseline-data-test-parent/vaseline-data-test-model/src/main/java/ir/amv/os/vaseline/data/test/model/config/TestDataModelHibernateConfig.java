package ir.amv.os.vaseline.data.test.model.config;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCityEntity;
import ir.amv.os.vaseline.data.test.model.server.entity.TestContinentEntity;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.server.entity.TestStateEntity;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestCityRepository;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestContinentRepository;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestCountryRepository;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestStateRepository;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Amir
 */
@Configuration
@Import(TestDataModelHibernateBaseConfig.class)
public class TestDataModelHibernateConfig {

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

}
