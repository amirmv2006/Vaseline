package ir.amv.os.vaseline.business.osgi.feature;

import cucumber.api.java.en.Given;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RunLocally;

import static ir.amv.os.vaseline.business.osgi.feature.helper.VaselineBusinessITHelper.vaselineBusinessWithPrerequisitesAndTestJpaModel;

public class BusinessBasicSteps {

    @Given("I have Vaseline Business with prerequisites and Test JPA model")
    @RunLocally
    public void iHaveVaselineDataWithPrerequisitesAndTestJpaModel() {
        vaselineBusinessWithPrerequisitesAndTestJpaModel();
    }
}
