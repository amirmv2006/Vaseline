package ir.amv.os.vaseline.business.spring.itest.basic;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.spring.itest.domain.city.ITestCityReadOnlyApi;
import ir.amv.os.vaseline.business.spring.itest.domain.city.TestCityBusinessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = VaselineITestBusinessBasicConfig.class, loader = SpringBootContextLoader.class)
public class BasicStepDefs {

    @Autowired
    ITestCityReadOnlyApi cityReadOnlyApi;

    private List<TestCityBusinessEntity> getAllResult;

    @When("I call getAll on Business Layer")
    public void iCallGetAllOnBusinessLayer() throws BaseBusinessException {
        getAllResult = cityReadOnlyApi.getAll();
    }


    @Then("I get {int} Cities as the result")
    public void iGetCitiesAsTheResultNamed(int count) {
        assertNotNull(getAllResult);
        assertEquals(count, getAllResult.size());
    }

    @Then("The lazy property \"state\" for these cities is not loaded")
    public void theLazyPropertyForTheseCitiesIsNotLoaded() {
        for (TestCityBusinessEntity resultCity : getAllResult) {
            assertNull("Lazy property 'state' should not be loaded", resultCity.getState());
        }
    }
}
