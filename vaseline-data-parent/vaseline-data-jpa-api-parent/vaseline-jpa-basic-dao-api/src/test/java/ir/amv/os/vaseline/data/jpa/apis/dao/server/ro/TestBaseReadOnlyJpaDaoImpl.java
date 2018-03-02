package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro;

import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
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
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestJpaDaoSpringConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
public class TestBaseReadOnlyJpaDaoImpl
        extends AbstractReadOnlyTest {

    @Value("classpath:jpaDaoTestData.json")
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
    protected IBaseReadOnlyDao<TestCountryEntity, Long> getCountryDao() {
        return countryDao;
    }

}