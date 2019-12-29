package ir.amv.os.vaseline.business.spring.itest.basic;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.spring.itest.domain.city.ITestCityReadOnlyApi;
import ir.amv.os.vaseline.business.spring.itest.domain.city.TestCityBusinessModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetCitiesByStateNameStepDefs {

    @Autowired
    ITestCityReadOnlyApi cityReadOnlyApi;

    private List<TestCityBusinessModel> queryResults;
    private String stateName;

    @When("I get cities of province with name {string}")
    public void iGetCitiesOfProvinceWithNameSouthHolland(String stateName) throws BaseBusinessException {
        queryResults = cityReadOnlyApi.getCitiesOfState(stateName);
        this.stateName = stateName;
    }

    @Then("The lazy property \"state\" for these cities is loaded")
    public void theLazyPropertyForTheseCitiesIsNotLoaded() {
        for (TestCityBusinessModel resultCity : queryResults) {
            assertNotNull("Lazy property 'state' should be loaded", resultCity.getState());
            assertEquals(stateName, resultCity.getState().getStateName());
        }
    }
}
