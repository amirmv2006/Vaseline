package ir.amv.os.vaseline.data.jpa.search.simple.api.server.dao;

import ir.amv.os.vaseline.data.search.simple.api.server.ro.IBaseSimpleSearchDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCountryDto;
import ir.amv.os.vaseline.data.test.model.test.AbstractSimpleSearchTest;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestJpaSimpleSearchSpringConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
public class TestBaseJpaSimpleSearchDaoImpl extends AbstractSimpleSearchTest {

    @Value("classpath:jpaSimpleSearchTestData.json")
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
    protected IBaseSimpleSearchDao<TestCountryEntity, TestCountryDto, Long> getCountryDao() {
        return countryDao;
    }
}
