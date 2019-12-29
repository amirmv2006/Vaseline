package ir.amv.os.vaseline.service.rest.itest.advanced.search;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles({"test", "AdvancedSearch"})
@ContextConfiguration(classes = VaselineITestRestAdvancedSearchConfig.class, loader = SpringBootContextLoader.class)
@AutoConfigureMockMvc
public class AdvancedSearchStepDefs {

    @Autowired
    private MockMvc mockMvc;

    @When("I count cities whose state's name starts with {string}")
    public void iCountCitiesWhoseStatesNameStartsWith(String stateNameStartsWith) throws Exception {
//        mockMvc.perform(get("/cityAS/searchCount")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(is(5)));
    }

    @Then("I get a city named {string} as result")
    public void iGetACityNamedAmsterdamAsResult(String cityName) {

    }
}
