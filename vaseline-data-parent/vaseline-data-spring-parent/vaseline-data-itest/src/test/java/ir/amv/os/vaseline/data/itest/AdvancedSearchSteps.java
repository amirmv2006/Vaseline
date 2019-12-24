package ir.amv.os.vaseline.data.itest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.amv.os.vaseline.data.itest.server.entity.TestCityEntity;
import ir.amv.os.vaseline.data.itest.server.repository.ITestCityRepository;
import ir.amv.os.vaseline.data.itest.shared.searchobject.ITestCitySearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.PropertyConditions;
import ir.amv.os.vaseline.data.search.advanced.api.server.proxy.SearchObjectProxyFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdvancedSearchSteps {

    @Autowired
    ITestCityRepository testCityRepository;
    private List<TestCityEntity> queryResult;

    @When("I query for cities whose name contains with {string}")
    public void queryCitiesWithNameContains(String cityName) {
        ITestCitySearchObject so = SearchObjectProxyFactory.proxy(ITestCitySearchObject.class);
        so.setCityName(PropertyConditions.contains(cityName));
        queryResult = testCityRepository.searchBySearchObject(so);
    }

    @Then("I get back cities with name {string} and {string}")
    public void queryResultContains(String firstCity, String secondCity) {
        Assert.assertTrue(queryResult.stream().anyMatch(c -> c.getCityName().equals(firstCity)));
        Assert.assertTrue(queryResult.stream().anyMatch(c -> c.getCityName().equals(secondCity)));
    }
}
