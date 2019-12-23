package ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro;

import ir.amv.os.vaseline.data.dao.basic.api.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryBusinessModel;
import ir.amv.os.vaseline.data.test.model.test.AbstractReadOnlyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestHibernateDaoSpringConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class TestBaseReadOnlyHibernateDaoImpl
        extends AbstractReadOnlyTest {

    @Value("classpath:hibernateDaoTestData.json")
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
    protected IBaseReadOnlyDao<Long, TestCountryBusinessModel> getCountryDao() {
        return countryDao;
    }


}