package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl;

import ir.amv.os.vaseline.base.advancedsearch.api.example.model.SearchJoinType;
import ir.amv.os.vaseline.base.advancedsearch.api.example.proxy.SearchObjectProxyFactory;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity.TestCity;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity.TestCountry;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity.TestState;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.repos.CityRepository;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.repos.CountryRepository;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.repos.StateRepository;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.so.ITestCitySearchObject;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.so.ITestCountrySearchObject;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.so.ITestStateSearchObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.PropertyConditions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by amv on 12/12/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvancedSearchByExampleJPAProviderUtilTest.TestConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
public class AdvancedSearchByExampleJPAProviderUtilTest {

    @EnableAutoConfiguration
    @EnableJpaRepositories(basePackages = "ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl")
    public static class TestConfig {
    }


    @Autowired
    CityRepository cityRepository;
    @Autowired
    StateRepository stateRepository;
    @Autowired
    CountryRepository countryRepository;

    @Autowired
    EntityManager em;

    @Test
    public void testSearchByExample() {
        ITestCitySearchObject so = SearchObjectProxyFactory.proxy(ITestCitySearchObject.class);
//        List<TestCity> search = cityRepository.search(so);
//        assertTrue(search.size() == 0);
        TestCountry country = new TestCountry();
        country.setCountryName("Test Country");
        countryRepository.save(country);
        TestState state = new TestState();
        state.setStateName("Test State");
        state.setCountry(country);
        stateRepository.save(state);
        TestCity city = new TestCity();
        city.setMap("MMMM");
        city.setName("Amir");
        city.setState(state);
        cityRepository.save(city);



//        so = SearchObjectProxyFactory.proxy(ITestCitySearchObject.class);
//        search = cityRepository.search(so);
//        assertEquals(1, search.size());
//        assertEquals(city, search.get(0));
//
//        so = SearchObjectProxyFactory.proxy(ITestCitySearchObject.class);
//        so.setName(equlas("amir"));
//        search = cityRepository.search(so);
//        assertEquals(0, search.size());
//
//        so = SearchObjectProxyFactory.proxy(ITestCitySearchObject.class);
//        so.setName(lower(equlas("amir")));
//        so.setMap(notEqulas("TT"));
//        search = cityRepository.search(so);
//        assertEquals(1, search.size());
//        assertEquals(city, search.get(0));

        ITestStateSearchObject stateEx = SearchObjectProxyFactory.proxy(ITestStateSearchObject.class);
        ITestCitySearchObject cities = SearchObjectProxyFactory.proxy(ITestCitySearchObject.class);
        cities.setName(lower(equlas("amir")));
        cities.setJoinType(SearchJoinType.LEFT);
        stateEx.setCities(cities);
        stateEx.setStateName(contains("State"));
        ITestCountrySearchObject countryEx = SearchObjectProxyFactory.proxy(ITestCountrySearchObject.class);
        countryEx.setJoinType(SearchJoinType.INNER);
        countryEx.setCountryName(upper(equlas("COUNTRY")));
        stateEx.setCountry(countryEx);
        List<TestState> result = stateRepository.search(stateEx);
        assertEquals(1, result.size());
        assertEquals(state, result.get(0));
    }
}
