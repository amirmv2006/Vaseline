package ir.amv.os.vaseline.data.jpa.spring.itest.basic;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCityReadOnlyRepo;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class BasicSteps {

    @Autowired
    ITestCityReadOnlyRepo testCityRepository;
    private long count;

    @When("I query city count")
    public void queryCityCount() {
        count = testCityRepository.count();
    }

    @Then("I get {int} as a result")
    public void iGetAsAResult(int expectedCount) {
        Assert.assertEquals(expectedCount, count);
    }

}
