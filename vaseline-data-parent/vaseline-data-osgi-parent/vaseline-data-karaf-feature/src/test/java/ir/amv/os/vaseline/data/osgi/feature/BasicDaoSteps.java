package ir.amv.os.vaseline.data.osgi.feature;

import cucumber.api.java.en.Given;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RunLocally;

import static ir.amv.os.vaseline.data.osgi.feature.helper.VaselineDataHelper.vaselineDataWithPrerequisitesAndTestJpaModel;

public class BasicDaoSteps {

    @Given("I have Vaseline Data with prerequisites and Test JPA model")
    @RunLocally
    public void iHaveVaselineDataWithPrerequisitesAndTestJpaModel() {
        vaselineDataWithPrerequisitesAndTestJpaModel();
    }
}
