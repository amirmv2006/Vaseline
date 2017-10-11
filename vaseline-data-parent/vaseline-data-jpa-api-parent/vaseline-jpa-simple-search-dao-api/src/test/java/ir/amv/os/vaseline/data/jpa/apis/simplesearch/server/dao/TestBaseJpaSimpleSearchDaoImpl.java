package ir.amv.os.vaseline.data.jpa.apis.simplesearch.server.dao;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCityDto;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestContinentDto;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCountryDto;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestStateDto;
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
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestJpaSimpleSearchSpringConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
public class TestBaseJpaSimpleSearchDaoImpl extends BaseDataModelTest {

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

    @Test
    public void testCountByExample() {
        TestCountryDto searchExample = new TestCountryDto();
        Long count = countryDao.countByExample(searchExample);
        assertEquals(3, count.longValue());
        searchExample.setCountryName("an");
        searchExample.setAreRacist(false);
        count = countryDao.countByExample(searchExample);
        assertEquals(2, count.longValue());
        searchExample.setPopulation(80_000_000L);
        count = countryDao.countByExample(searchExample);
        assertEquals(1, count.longValue());
    }

    @Test
    public void testSearchByExample() {
        TestCountryDto searchExample = new TestCountryDto();
        List<TestCountryEntity> searchResult = countryDao.searchByExample(searchExample);
        assertEquals(3, searchResult.size());
        searchExample.setCountryName("an");
        searchExample.setAreRacist(false);
        searchResult = countryDao.searchByExample(searchExample);
        assertEquals(2, searchResult.size());
        searchExample.setPopulation(80_000_000L);
        searchResult = countryDao.searchByExample(searchExample);
        assertEquals(1, searchResult.size());
        assertEquals(countriesMap.get("Iran"), searchResult.get(0));
        searchExample = new TestCountryDto();
        TestContinentDto continentExample = new TestContinentDto();
        continentExample.setContinentName("North America");
        searchExample.setContinent(continentExample);
        searchResult = countryDao.searchByExample(searchExample);
        assertEquals(1, searchResult.size());
        assertEquals(countriesMap.get("Canada"), searchResult.get(0));
        searchExample = new TestCountryDto();
        Set<TestStateDto> stateExamples = new HashSet<>();
        TestStateDto tehranStateExample = new TestStateDto();
        tehranStateExample.setStateName("Tehran");
        stateExamples.add(tehranStateExample);
        TestStateDto selangorStateExample = new TestStateDto();
        selangorStateExample.setStateName("Selangor");
        stateExamples.add(selangorStateExample);
        searchExample.setStates(stateExamples);
        searchResult = countryDao.searchByExample(searchExample);
        assertEquals(2, searchResult.size());
        searchExample = new TestCountryDto();
        stateExamples = new HashSet<>();
        tehranStateExample = new TestStateDto();
        Set<TestCityDto> tehranCities = new HashSet<>();
        TestCityDto tehranCity = new TestCityDto();
        tehranCity.setCityName("Tehran");
        tehranCities.add(tehranCity);
        tehranStateExample.setCities(tehranCities);
        stateExamples.add(tehranStateExample);
        selangorStateExample = new TestStateDto();
        Set<TestCityDto> selangorCities = new HashSet<>();
        TestCityDto cyberjayaCity = new TestCityDto();
        cyberjayaCity.setCityName("Cyberjaya");
        selangorCities.add(cyberjayaCity);
        selangorStateExample.setCities(selangorCities);
        stateExamples.add(selangorStateExample);
        searchExample.setStates(stateExamples);
        searchResult = countryDao.searchByExample(searchExample);
        assertEquals(2, searchResult.size());
    }

    @Test
    public void testSearchByExamplePaging() {
        PagingDto pagingDto = new PagingDto(Collections.singletonList(new SortDto("countryName", true)), 0, 1);
        TestCountryDto searchExample = new TestCountryDto();
        searchExample.setCountryName("an");
        searchExample.setAreRacist(false);
        List<TestCountryEntity> page = countryDao.getAll(pagingDto);
        assertEquals(1, page.size());
        assertEquals("Canada", page.get(0).getCountryName());
    }

    @Test
    public void testScrollByExample() {

    }
}
