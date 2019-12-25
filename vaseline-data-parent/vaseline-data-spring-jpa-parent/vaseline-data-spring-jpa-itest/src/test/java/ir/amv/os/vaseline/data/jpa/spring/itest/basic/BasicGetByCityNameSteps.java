package ir.amv.os.vaseline.data.jpa.spring.itest.basic;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCityReadOnlyRepo;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.TestCityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BasicGetByCityNameSteps {

    @Autowired
    TransactionTemplate transactionTemplate;

    @Autowired
    ITestCityReadOnlyRepo testCityRepository;
    private Optional<TestCityEntity> queryResult;

    @When("I query city by name {string}")
    public void QueryCityByName(String cityName) {
        queryResult = testCityRepository.findByCityName(cityName);
    }

    @Then("I should get back {int} city named {string}")
    public void checkQueryNyNameResults(int countResult, String cityName) {
        if (countResult > 0) {
            assertTrue(queryResult.isPresent());
            assertEquals(cityName, queryResult.get().getCityName());
        } else {
            assertFalse(queryResult.isPresent());
        }
    }
}
