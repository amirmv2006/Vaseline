package ir.amv.os.vaseline.data.test.model.config;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCityBusinessModel;
import ir.amv.os.vaseline.data.test.model.server.entity.TestContinentBusinessModel;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryBusinessModel;
import ir.amv.os.vaseline.data.test.model.server.entity.TestStateBusinessModel;
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
            public TestContinentBusinessModel save(TestContinentBusinessModel testContinentEntity) {
                sessionFactory.getCurrentSession().save(testContinentEntity);
                return testContinentEntity;
            }

            @Override
            public void delete(TestContinentBusinessModel testContinentEntity) {
                sessionFactory.getCurrentSession().delete(testContinentEntity);
            }
        };
    }

    @Bean
    public ITestCountryRepository testCountryRepository(SessionFactory sessionFactory) {
        return new ITestCountryRepository() {
            @Override
            public TestCountryBusinessModel save(TestCountryBusinessModel testCountryEntity) {
                sessionFactory.getCurrentSession().save(testCountryEntity);
                return testCountryEntity;
            }

            @Override
            public void delete(TestCountryBusinessModel testCountryEntity) {
                sessionFactory.getCurrentSession().delete(testCountryEntity);
            }
        };
    }

    @Bean
    public ITestStateRepository testStateRepository(SessionFactory sessionFactory) {
        return new ITestStateRepository() {
            @Override
            public TestStateBusinessModel save(TestStateBusinessModel testStateEntity) {
                sessionFactory.getCurrentSession().save(testStateEntity);
                return testStateEntity;
            }

            @Override
            public void delete(TestStateBusinessModel testStateEntity) {
                sessionFactory.getCurrentSession().delete(testStateEntity);
            }
        };
    }

    @Bean
    public ITestCityRepository testCityRepository(SessionFactory sessionFactory) {
        return new ITestCityRepository() {
            @Override
            public TestCityBusinessModel save(TestCityBusinessModel testCityEntity) {
                sessionFactory.getCurrentSession().save(testCityEntity);
                return testCityEntity;
            }

            @Override
            public void delete(TestCityBusinessModel testCityEntity) {
                sessionFactory.getCurrentSession().delete(testCityEntity);
            }
        };
    }

}
