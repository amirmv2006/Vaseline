package ir.amv.os.vaseline.data.jpa.apis.simplesearch.server.dao;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.apis.search.simple.server.ro.IBaseSimpleSearchDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCityDto;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestContinentDto;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCountryDto;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestStateDto;
import ir.amv.os.vaseline.data.test.model.test.AbstractSimpleSearchTest;
import ir.amv.os.vaseline.data.test.model.test.BaseDataModelTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
