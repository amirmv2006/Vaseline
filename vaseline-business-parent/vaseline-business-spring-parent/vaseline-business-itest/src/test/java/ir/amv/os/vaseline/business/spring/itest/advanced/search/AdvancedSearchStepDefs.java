package ir.amv.os.vaseline.business.spring.itest.advanced.search;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.spring.itest.domain.city.ITestCityAdvancedSearchApi;
import ir.amv.os.vaseline.data.advanced.search.api.model.condition.PropertyConditions;
import ir.amv.os.vaseline.data.advanced.search.api.proxy.SearchObjectProxyFactory;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCitySearchObject;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.state.ITestStateSearchObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = VaselineITestBusinessAdvancedSearchConfig.class, loader = SpringBootContextLoader.class)
public class AdvancedSearchStepDefs {

    @Autowired
    ITestCityAdvancedSearchApi cityAdvancedSearchApi;
    private Long countResult;

    @When("I count cities whose state's name ends with {string}")
    public void iCountCitiesWhoseStatesNameEndsWithHolland(String stateNameTail) throws BaseBusinessException {
        ITestCitySearchObject citySO = SearchObjectProxyFactory.proxy(ITestCitySearchObject.class);
        ITestStateSearchObject stateSO = SearchObjectProxyFactory.proxy(ITestStateSearchObject.class);
        stateSO.setStateName(PropertyConditions.endswith(stateNameTail));
        citySO.setState(stateSO);
        countResult = cityAdvancedSearchApi.countBySearchObject(citySO);
    }

    @Then("I get {int} as the count result")
    public void iGetCitiesAsTheResultNamed(int count) {
        assertEquals(count, countResult.longValue());
    }
}
