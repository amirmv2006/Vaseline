package ir.amv.os.vaseline.service.osgi.feature.helper;

import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RemoteObjectFactory;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.helper.KarafOptionsHelper;

import static ir.amv.os.vaseline.business.osgi.feature.helper.VaselineBusinessITHelper.vaselineBusinessWithPrerequisitesAndTestJpaModel;

public class VaselineServiceITHelper {

    public static void vaselineServiceWithPrerequisitesAndTestJpaModel() {
        vaselineBusinessWithPrerequisitesAndTestJpaModel();
        RemoteObjectFactory.getInstance().getRemoteKarafEnvironment().addOption(
                KarafOptionsHelper.deployFeature("com.github.amirmv2006.service.osgi", "vaseline-service-karaf-feature", "vaseline-service-karaf-feature")
        );
    }
}
