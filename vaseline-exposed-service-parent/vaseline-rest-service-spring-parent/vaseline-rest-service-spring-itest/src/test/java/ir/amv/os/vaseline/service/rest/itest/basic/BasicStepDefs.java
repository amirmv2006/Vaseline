package ir.amv.os.vaseline.service.rest.itest.basic;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.amv.os.vaseline.service.rest.itest.domain.city.TestCityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test", "basic"})
@ContextConfiguration(classes = VaselineITestRestBasicConfig.class, loader = SpringBootContextLoader.class)
public class BasicStepDefs {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private ResponseEntity<TestCityDto[]> cities;

    @When("I send {string} on {string} endpoint")
    public void iSendGETOnCityEndpoint(String httpMethod, String url) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        cities = restTemplate.getForEntity("http://localhost:" + port + url,
                TestCityDto[].class);;
    }

    @Then("I get {int} Cities as the result")
    public void iGetCitiesAsTheResult(int count) {
        assertNotNull(cities.getBody());
        assertEquals(count, cities.getBody().length);
    }
}
