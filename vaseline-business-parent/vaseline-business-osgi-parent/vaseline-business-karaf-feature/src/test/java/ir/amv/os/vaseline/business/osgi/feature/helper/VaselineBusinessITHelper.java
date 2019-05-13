package ir.amv.os.vaseline.business.osgi.feature.helper;

import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RemoteObjectFactory;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.helper.KarafOptionsHelper;

import static ir.amv.os.vaseline.data.osgi.feature.helper.VaselineDataHelper.vaselineDataWithPrerequisitesAndTestJpaModel;

public class VaselineBusinessITHelper {

    public static void vaselineBusinessWithPrerequisitesAndTestJpaModel() {
        vaselineDataWithPrerequisitesAndTestJpaModel();
        RemoteObjectFactory.getInstance().getRemoteKarafEnvironment().addOption(
                KarafOptionsHelper.deployFeature("com.github.amirmv2006.business.osgi", "vaseline-business-karaf-feature", "vaseline-business-karaf-feature")
        );
    }
}
