package ir.amv.os.vaseline.data.jpa.spring.itest.advanced.search;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.amv.os.vaseline.data.advanced.search.api.proxy.SearchObjectProxyFactory;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCityAdvancedSearchRepo;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCitySearchObject;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.TestCityEntity;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.country.ITestCountrySearchObject;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.state.ITestStateSearchObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.List;

import static ir.amv.os.vaseline.data.advanced.search.api.model.condition.PropertyConditions.contains;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = VaselineITestDataAdvancedSearchConfig.class, loader = SpringBootContextLoader.class)
public class AdvancedSearchSteps {

    @Autowired
    ITestCityAdvancedSearchRepo testCityRepository;
    private List<TestCityEntity> queryResult;

    @When("I query for cities whose name contains with {string}")
    public void queryCitiesWithNameContains(String cityName) {
        ITestCitySearchObject so = SearchObjectProxyFactory.proxy(ITestCitySearchObject.class);
        so.setCityName(contains(cityName));
        queryResult = testCityRepository.searchBySearchObject(so);
    }

    @Then("I get back cities with name {string}")
    public void queryResultContains(String cityNames) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<String>> typeReference = new TypeReference<List<String>>() {
        };
        List<String> o = objectMapper.readValue(cityNames, typeReference);
        o.stream()
                .map(String::trim)
                .forEach(cityName -> Assert.assertTrue(queryResult.stream().anyMatch(c -> c.getCityName().equals(cityName))));
    }

    @When("I query for cities whose country's name contains {string}")
    public void queryCitiesWithCountryNameContains(String countryName) {
        ITestCitySearchObject so = SearchObjectProxyFactory.proxy(ITestCitySearchObject.class);
        ITestStateSearchObject state = SearchObjectProxyFactory.proxy(ITestStateSearchObject.class);
        ITestCountrySearchObject country = SearchObjectProxyFactory.proxy(ITestCountrySearchObject.class);
        country.setCountryName(contains(countryName));
        state.setCountry(country);
        so.setState(state);
        queryResult = testCityRepository.searchBySearchObject(so);
    }
}
