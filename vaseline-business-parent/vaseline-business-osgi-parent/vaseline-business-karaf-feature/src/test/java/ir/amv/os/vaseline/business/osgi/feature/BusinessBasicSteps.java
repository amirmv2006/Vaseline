package ir.amv.os.vaseline.business.osgi.feature;

import cucumber.api.java.en.Given;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RemoteObjectFactory;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RunLocally;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.helper.KarafOptionsHelper;

import static ir.amv.os.vaseline.data.osgi.feature.helper.VaselineDataHelper.vaselineDataWithPrerequisitesAndTestJpaModel;

public class BusinessBasicSteps {

    @Given("I have Vaseline Business with prerequisites and Test JPA model")
    @RunLocally
    public void iHaveVaselineDataWithPrerequisitesAndTestJpaModel() {
        vaselineDataWithPrerequisitesAndTestJpaModel();
        RemoteObjectFactory.getInstance().getRemoteKarafEnvironment().addOption(
                KarafOptionsHelper.deployFeature("com.github.amirmv2006.business.osgi", "vaseline-business-karaf-feature", "vaseline-business-karaf-feature")
        );
    }
}
