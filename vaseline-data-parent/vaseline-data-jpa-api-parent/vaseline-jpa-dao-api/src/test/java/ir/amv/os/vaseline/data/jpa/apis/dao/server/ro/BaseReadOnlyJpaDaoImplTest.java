package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro;

import ir.amv.os.vaseline.data.test.model.config.TestDataModelConfig;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.test.BaseDataModelTest;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDataModelConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
public class BaseReadOnlyJpaDaoImplTest
        extends BaseDataModelTest {

    @Value("classpath:testData.json")
    private Resource testData;

    @Before
    public void setup() throws IOException {
        setupDataFromJson(testData.getInputStream());
    }

    @Transactional
    public void testCountryIsThere() {
        Iterable<TestCountryEntity> all = countryRepository.findAll();
        all.forEach((country) -> {
            System.out.println("country.getCountryName() = " + country.getCountryName());
            System.out.println("country.getPopulation() = " + country.getPopulation());
        });
    }
}
