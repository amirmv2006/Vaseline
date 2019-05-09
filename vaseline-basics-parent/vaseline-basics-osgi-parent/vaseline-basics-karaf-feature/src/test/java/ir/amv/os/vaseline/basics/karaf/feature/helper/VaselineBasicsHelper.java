package ir.amv.os.vaseline.basics.karaf.feature.helper;

import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RegisterService;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RemoteObjectFactory;
import org.osgi.framework.BundleContext;

import java.util.*;

import static ir.amv.os.vaseline.testing.integration.cucumber.karaf.helper.KarafOptionsHelper.deployFeature;

public class VaselineBasicsHelper {

    public static void vaselineBasicsWithPrerequisites() {
        RemoteObjectFactory.getInstance().getRemoteKarafEnvironment().addExtraClasses(VaselineBasicsHelper.class, RegisterService.class);
        RemoteObjectFactory.getInstance().getRemoteKarafEnvironment().addOptions(Collections.singletonList(
                deployFeature("com.github.amirmv2006.basics.osgi", "vaseline-basics-karaf-feature", "vaseline-basics-karaf-feature")
        ));
    }

    public static <S> void registerService(
            BundleContext bundleContext,
            Class<S> serviceClass,
            S service,
            Map<String, Object> properties) {
        Hashtable<String, Object> propsCopy = new Hashtable<>();
        if (properties != null) {
            propsCopy.putAll(properties);
        }
        bundleContext.registerService(serviceClass, service, propsCopy);
    }
}
