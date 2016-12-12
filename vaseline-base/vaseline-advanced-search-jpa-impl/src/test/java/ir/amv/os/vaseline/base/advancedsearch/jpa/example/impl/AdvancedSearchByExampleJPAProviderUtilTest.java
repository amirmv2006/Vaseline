package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl;

import ir.amv.os.vaseline.base.advancedsearch.api.example.proxy.SearchObjectProxyFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;
import static org.junit.Assert.*;
import static ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.PropertyConditions.*;

/**
 * Created by amv on 12/12/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvancedSearchByExampleJPAProviderUtilTest.TestConfig.class)
public class AdvancedSearchByExampleJPAProviderUtilTest {

    @EnableAutoConfiguration
    @EnableJpaRepositories(basePackages = "ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl")
    public static class TestConfig {
    }


    @Autowired
    CityRepository cityRepository;

    @Autowired
    EntityManager em;

    @Test
    public void testSearchByExample() {
        ICitySearchObject so = SearchObjectProxyFactory.proxy(ICitySearchObject.class);
        List<City> search = cityRepository.search(so);
        assertTrue(search.size() == 0);
        City city = new City();
        city.setCountry("IR");
        city.setMap("MMMM");
        city.setName("Amir");
        city.setState("Enabled");
        cityRepository.save(city);

        so = SearchObjectProxyFactory.proxy(ICitySearchObject.class);
        search = cityRepository.search(so);
        assertEquals(1, search.size());
        assertEquals(city, search.get(0));

        so = SearchObjectProxyFactory.proxy(ICitySearchObject.class);
        so.setName(equlas("amir"));
        search = cityRepository.search(so);
        assertEquals(0, search.size());

        so = SearchObjectProxyFactory.proxy(ICitySearchObject.class);
        so.setName(lower(equlas("amir")));
        so.setCountry(contains("R"));
        so.setMap(notEqulas("TT"));
        search = cityRepository.search(so);
        assertEquals(1, search.size());
        assertEquals(city, search.get(0));

    }
}
