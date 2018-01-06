package ir.amv.os.vaseline.service.osgi.feature;

import ir.amv.os.vaseline.basics.osgi.testing.util.AbstractBundleChecker;
import ir.amv.os.vaseline.basics.osgi.testing.util.BundleStartedChecker;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.InvalidSyntaxException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * @author Amir
 */
public class VaselineServiceWithoutSearchFeatureIntegrationTest
        extends AbstractVaselineServiceIntegrationTest {

    @Test
    public void testBundles() throws InvalidSyntaxException, ClassNotFoundException, InterruptedException {
        assertNotNull(bundleContext);
        List<AbstractBundleChecker> bundleCheckers = new ArrayList<>();
        bundleCheckers.add(new BundleStartedChecker("vaseline-service-basic-layer-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-service-basic-layerimpl-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-service-multidao-layer-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-service-multidao-layerimpl-api"));

        for (AbstractBundleChecker bundleChecker : bundleCheckers) {
            bundleChecker.checkBundle(bundleContext);
        }
        Bundle[] bundles = bundleContext.getBundles();
        List<String> bannedBundles = Arrays.asList(
                "vaseline-service-simple-search-layer-api",
                "vaseline-service-simple-search-layerimpl-api",
                "vaseline-service-advanced-search-layer-api",
                "vaseline-service-advanced-search-layerimpl-api"
        );
        for (Bundle bundle : bundles) {
            if (bannedBundles.contains(bundle.getSymbolicName())) {
                fail();
            }
        }
    }

    @Override
    protected String[] getTestFeatures() {
        return new String[]{
                "vaseline-service-basic-layer-api",
                "vaseline-service-basic-layerimpl-api",
                "vaseline-service-multidao-layer-api",
                "vaseline-service-multidao-layerimpl-api"
        };
    }
}
