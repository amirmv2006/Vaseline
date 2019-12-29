package ir.amv.os.vaseline.data.jpa.spring.itest.basic;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCityReadOnlyRepo;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.state.ITestStateJpaSpringRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = VaselineITestDataBasicConfig.class, loader = SpringBootContextLoader.class)
public class BasicSteps {

    @Autowired
    ITestCityReadOnlyRepo testCityRepository;
    @Autowired
    ITestStateJpaSpringRepository testStateRepository;
    private long count;

    @When("I query city count")
    public void queryCityCount() {
        count = testCityRepository.count();
    }

    @Then("I get {int} as count result")
    public void iGetAsAResult(int expectedCount) {
        Assert.assertEquals(expectedCount, count);
    }

    @When("I query province count")
    public void iQueryProvinceCount() {
        count = testStateRepository.count();
    }
}
