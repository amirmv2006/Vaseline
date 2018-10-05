package ir.amv.os.vaseline.data.hibernate.apis.advancedsearch.server.dao;

import ch.qos.logback.core.status.Status;
import ir.amv.os.vaseline.data.apis.search.advanced.server.ro.IBaseAdvancedSearchDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCountrySearchObject;
import ir.amv.os.vaseline.data.test.model.test.AbstractAdvancedSearchTest;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestHibernateAdvancedSearchSpringConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
public class TestBaseHibernateAdvancedSearchDaoImpl
        extends AbstractAdvancedSearchTest {

    @Value("classpath:hibernateAdvancedSearchTestData.json")
    private Resource testData;

    @Inject
    private ITestCountryDao countryDao;

    @Before
    public void setup() throws IOException {
        setupDataFromJson(testData.getInputStream());
    }

    @After
    public void tearDown() {
        tearDownAll();
    }


    @Override
    protected IBaseAdvancedSearchDao<TestCountryEntity, ITestCountrySearchObject, Long> getCountryDao() {
        return countryDao;
    }
}
