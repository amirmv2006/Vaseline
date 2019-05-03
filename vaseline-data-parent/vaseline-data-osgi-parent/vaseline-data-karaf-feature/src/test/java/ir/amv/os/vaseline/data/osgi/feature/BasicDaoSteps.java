package ir.amv.os.vaseline.data.osgi.feature;

import cucumber.api.java.en.Given;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.SetupKaraf;

import static ir.amv.os.vaseline.data.osgi.feature.helper.VaselineDataHelper.vaselineDataWithPrerequisitesAndTestJpaModel;

public class BasicDaoSteps {

    @Given("I have Vaseline Data with prerequisites and Test JPA model")
    @SetupKaraf
    public void iHaveVaselineDataWithPrerequisitesAndTestJpaModel() {
        vaselineDataWithPrerequisitesAndTestJpaModel();
    }
}
