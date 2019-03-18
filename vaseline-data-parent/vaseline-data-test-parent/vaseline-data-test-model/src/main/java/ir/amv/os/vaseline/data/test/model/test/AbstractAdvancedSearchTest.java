package ir.amv.os.vaseline.data.test.model.test;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.PropertyConditions;
import ir.amv.os.vaseline.data.search.advanced.api.server.proxy.SearchObjectProxyFactory;
import ir.amv.os.vaseline.data.search.advanced.api.server.ro.IBaseAdvancedSearchDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestContinentSearchObject;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCountrySearchObject;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.impl.TestCountrySearchObjectImpl;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Amir
 */
@Transactional
public abstract class AbstractAdvancedSearchTest
        extends BaseDataModelTest {

    protected abstract IBaseAdvancedSearchDao<TestCountryEntity, ITestCountrySearchObject, Long> getCountryDao();

    @Test
    public void testCountBySearchObject() {
        ITestCountrySearchObject countrySearchObject = new TestCountrySearchObjectImpl();
        countrySearchObject.setCountryName(PropertyConditions.equlas("Iran"));
        Long count = getCountryDao().countBySearchObject(countrySearchObject);
        assertEquals(1, count.longValue());
        countrySearchObject = SearchObjectProxyFactory.proxy(ITestCountrySearchObject.class);
        countrySearchObject.setPopulation(PropertyConditions.greaterThan(35_000_000L));
        count = getCountryDao().countBySearchObject(countrySearchObject);
        assertEquals(2, count.longValue());
        countrySearchObject = SearchObjectProxyFactory.proxy(ITestCountrySearchObject.class);
        ITestContinentSearchObject continentSearchObject = SearchObjectProxyFactory.proxy(ITestContinentSearchObject.class);
        continentSearchObject.setContinentName(PropertyConditions.equlas("North America"));
        countrySearchObject.setContinent(continentSearchObject);
        count = getCountryDao().countBySearchObject(countrySearchObject);
        assertEquals(1, count.longValue());
    }

    @Test
    public void testSearchBySearchObject() {
        ITestCountrySearchObject countrySearchObject = new TestCountrySearchObjectImpl();
        countrySearchObject.setCountryName(PropertyConditions.equlas("Iran"));
        List<TestCountryEntity> searchResult = getCountryDao().searchBySearchObject(countrySearchObject);
        assertEquals(1, searchResult.size());
        assertEquals(countriesMap.get("Iran"), searchResult.get(0));
        countrySearchObject = SearchObjectProxyFactory.proxy(ITestCountrySearchObject.class);
        countrySearchObject.setPopulation(PropertyConditions.greaterThan(35_000_000L));
        searchResult = getCountryDao().searchBySearchObject(countrySearchObject);
        assertEquals(2, searchResult.size());
        countrySearchObject = SearchObjectProxyFactory.proxy(ITestCountrySearchObject.class);
        ITestContinentSearchObject continentSearchObject = SearchObjectProxyFactory.proxy(ITestContinentSearchObject.class);
        continentSearchObject.setContinentName(PropertyConditions.equlas("North America"));
        countrySearchObject.setContinent(continentSearchObject);
        searchResult = getCountryDao().searchBySearchObject(countrySearchObject);
        assertEquals(1, searchResult.size());
        assertEquals(countriesMap.get("Canada"), searchResult.get(0));
    }

    @Test
    public void testSearchBySearchObjectPaging() {
        PagingDto pagingDto = new PagingDto(Collections.singletonList(new SortDto("countryName", true)), 0, 1);
        ITestCountrySearchObject countrySearchObject = SearchObjectProxyFactory.proxy(ITestCountrySearchObject.class);
        countrySearchObject.setPopulation(PropertyConditions.greaterThan(35_000_000L));
        List<TestCountryEntity> page = getCountryDao().searchBySearchObject(countrySearchObject, pagingDto);
        assertEquals(1, page.size());
        assertEquals("Canada", page.get(0).getCountryName());
    }

    @Test
    public void testScrollBySearchObject() {

    }

}
