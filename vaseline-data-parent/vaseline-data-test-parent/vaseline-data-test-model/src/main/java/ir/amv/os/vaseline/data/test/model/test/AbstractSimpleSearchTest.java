package ir.amv.os.vaseline.data.test.model.test;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.search.simple.api.server.ro.IBaseSimpleSearchDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCityDto;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestContinentDto;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCountryDto;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestStateDto;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Amir
 */
@Transactional
public abstract class AbstractSimpleSearchTest
        extends BaseDataModelTest {

    protected abstract IBaseSimpleSearchDao<Long, TestCountryEntity, TestCountryDto> getCountryDao();

    @Test
    public void testCountByExample() {
        TestCountryDto searchExample = new TestCountryDto();
        Long count = getCountryDao().countByExample(searchExample);
        assertEquals(3, count.longValue());
        searchExample.setCountryName("an");
        searchExample.setAreRacist(false);
        count = getCountryDao().countByExample(searchExample);
        assertEquals(2, count.longValue());
        searchExample.setPopulation(80_000_000L);
        count = getCountryDao().countByExample(searchExample);
        assertEquals(1, count.longValue());
    }

    @Test
    public void testSearchByExample() {
        TestCountryDto searchExample = new TestCountryDto();
        List<TestCountryEntity> searchResult = getCountryDao().searchByExample(searchExample);
        assertEquals(3, searchResult.size());
        searchExample.setCountryName("an");
        searchExample.setAreRacist(false);
        searchResult = getCountryDao().searchByExample(searchExample);
        assertEquals(2, searchResult.size());
        searchExample.setPopulation(80_000_000L);
        searchResult = getCountryDao().searchByExample(searchExample);
        assertEquals(1, searchResult.size());
        assertEquals(countriesMap.get("Iran"), searchResult.get(0));
        searchExample = new TestCountryDto();
        TestContinentDto continentExample = new TestContinentDto();
        continentExample.setContinentName("North America");
        searchExample.setContinent(continentExample);
        searchResult = getCountryDao().searchByExample(searchExample);
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
        searchResult = getCountryDao().searchByExample(searchExample);
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
        searchResult = getCountryDao().searchByExample(searchExample);
        assertEquals(2, searchResult.size());
    }

    @Test
    public void testSearchByExamplePaging() {
        PagingDto pagingDto = new PagingDto(Collections.singletonList(new SortDto("countryName", true)), 0, 1);
        TestCountryDto searchExample = new TestCountryDto();
        searchExample.setCountryName("an");
        searchExample.setAreRacist(false);
        List<TestCountryEntity> page = getCountryDao().getAll(pagingDto);
        assertEquals(1, page.size());
        assertEquals("Canada", page.get(0).getCountryName());
    }

    @Test
    public void testScrollByExample() {

    }
}
