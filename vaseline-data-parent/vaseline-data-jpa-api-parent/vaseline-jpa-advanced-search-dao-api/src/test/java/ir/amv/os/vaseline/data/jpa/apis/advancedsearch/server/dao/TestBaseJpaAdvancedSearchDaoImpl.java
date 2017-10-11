package ir.amv.os.vaseline.data.jpa.apis.advancedsearch.server.dao;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.PropertyConditions;
import ir.amv.os.vaseline.data.apis.search.advanced.server.proxy.SearchObjectProxyFactory;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCountryDto;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestContinentSearchObject;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCountrySearchObject;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.impl.TestCountrySearchObjectImpl;
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
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestJpaAdvancedSearchSpringConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
public class TestBaseJpaAdvancedSearchDaoImpl
        extends BaseDataModelTest {

    @Value("classpath:jpaAdvancedSearchTestData.json")
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
    public void testCountBySearchObject() {
        ITestCountrySearchObject countrySearchObject = new TestCountrySearchObjectImpl();
        countrySearchObject.setCountryName(PropertyConditions.equlas("Iran"));
        Long count = countryDao.countBySearchObject(countrySearchObject);
        assertEquals(1, count.longValue());
        countrySearchObject = SearchObjectProxyFactory.proxy(ITestCountrySearchObject.class);
        countrySearchObject.setPopulation(PropertyConditions.greaterThan(35_000_000L));
        count = countryDao.countBySearchObject(countrySearchObject);
        assertEquals(2, count.longValue());
        countrySearchObject = SearchObjectProxyFactory.proxy(ITestCountrySearchObject.class);
        ITestContinentSearchObject continentSearchObject = SearchObjectProxyFactory.proxy(ITestContinentSearchObject.class);
        continentSearchObject.setContinentName(PropertyConditions.equlas("North America"));
        countrySearchObject.setContinent(continentSearchObject);
        count = countryDao.countBySearchObject(countrySearchObject);
        assertEquals(1, count.longValue());
    }

    @Test
    public void testSearchBySearchObject() {
        ITestCountrySearchObject countrySearchObject = new TestCountrySearchObjectImpl();
        countrySearchObject.setCountryName(PropertyConditions.equlas("Iran"));
        List<TestCountryEntity> searchResult = countryDao.searchBySearchObject(countrySearchObject);
        assertEquals(1, searchResult.size());
        assertEquals(countriesMap.get("Iran"), searchResult.get(0));
        countrySearchObject = SearchObjectProxyFactory.proxy(ITestCountrySearchObject.class);
        countrySearchObject.setPopulation(PropertyConditions.greaterThan(35_000_000L));
        searchResult = countryDao.searchBySearchObject(countrySearchObject);
        assertEquals(2, searchResult.size());
        countrySearchObject = SearchObjectProxyFactory.proxy(ITestCountrySearchObject.class);
        ITestContinentSearchObject continentSearchObject = SearchObjectProxyFactory.proxy(ITestContinentSearchObject.class);
        continentSearchObject.setContinentName(PropertyConditions.equlas("North America"));
        countrySearchObject.setContinent(continentSearchObject);
        searchResult = countryDao.searchBySearchObject(countrySearchObject);
        assertEquals(1, searchResult.size());
        assertEquals(countriesMap.get("Canada"), searchResult.get(0));
    }

    @Test
    public void testSearchBySearchObjectPaging() {
        PagingDto pagingDto = new PagingDto(Collections.singletonList(new SortDto("countryName", true)), 0, 1);
        ITestCountrySearchObject countrySearchObject = SearchObjectProxyFactory.proxy(ITestCountrySearchObject.class);
        countrySearchObject.setPopulation(PropertyConditions.greaterThan(35_000_000L));
        List<TestCountryEntity> page = countryDao.searchBySearchObject(countrySearchObject, pagingDto);
        assertEquals(1, page.size());
        assertEquals("Canada", page.get(0).getCountryName());
    }

    @Test
    public void testScrollBySearchObject() {

    }
}
