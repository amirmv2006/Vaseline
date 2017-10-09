package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.test.BaseDataModelTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestJpaDaoSpringConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
public class BaseReadOnlyJpaDaoImplTest
        extends BaseDataModelTest {

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

    @Test
    public void testGetById() {
        TestCountryEntity iran = countriesMap.get("Iran");
        TestCountryEntity byId = countryDao.getById(iran.getId());
        assertEquals(iran, byId);
    }

    @Test
    public void testGetByIdDetached() {
        TestCountryEntity iran = countriesMap.get("Iran");
        TestCountryEntity byId = countryDao.getById(iran.getId());
        assertEquals(iran, byId);
    }

//    public void testCountAllApproximately();

    @Test
    public void testCountAll() {
        Long countriesCount = countryDao.countAll();
        assertEquals(3, countriesCount.longValue());
    }

    @Test
    public void testGetAll() {
        List<TestCountryEntity> all = countryDao.getAll();
        assertEquals(3, all.size());
        for (TestCountryEntity anAll : all) {
            assertEquals(countriesMap.get(anAll.getCountryName()), anAll);
        }
    }

    @Test
    public void testGetAllPaging() {
        PagingDto pagingDto = new PagingDto(Collections.singletonList(new SortDto("countryName", true)), 0, 1);
        List<TestCountryEntity> page = countryDao.getAll(pagingDto);
        assertEquals(1, page.size());
        assertEquals("Canada", page.get(0).getCountryName());
    }

    @Test
    public void testScrollAll() {

    }

}