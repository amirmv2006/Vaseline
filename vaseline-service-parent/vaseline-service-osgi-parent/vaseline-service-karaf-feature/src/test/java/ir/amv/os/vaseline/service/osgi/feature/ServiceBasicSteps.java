package ir.amv.os.vaseline.service.osgi.feature;

import cucumber.api.java.en.Given;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RunLocally;

import static ir.amv.os.vaseline.service.osgi.feature.helper.VaselineServiceITHelper.vaselineServiceWithPrerequisitesAndTestJpaModel;

public class ServiceBasicSteps {
    @Given("I have Vaseline Service with prerequisites and Test JPA model")
    @RunLocally
    public void iHaveVaselineServiceWithPrerequisitesAndTestJPAModel() {
        vaselineServiceWithPrerequisitesAndTestJpaModel();
    }


}
