package ir.amv.os.vaseline.business.osgi.feature;

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
public class VaselineBusinessWithoutSearchFeatureIntegrationTest
        extends AbstractVaselineBusinessIntegrationTest {

    @Test
    public void testBundles() throws InvalidSyntaxException, ClassNotFoundException, InterruptedException {
        assertNotNull(bundleContext);
        List<AbstractBundleChecker> bundleCheckers = new ArrayList<>();
        bundleCheckers.add(new BundleStartedChecker("vaseline-business-basic-layer-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-business-basic-layerimpl-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-business-multidao-layer-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-business-multidao-layerimpl-api"));

        for (AbstractBundleChecker bundleChecker : bundleCheckers) {
            bundleChecker.checkBundle(bundleContext);
        }
        Bundle[] bundles = bundleContext.getBundles();
        List<String> bannedBundles = Arrays.asList(
                "vaseline-business-simple-search-layer-api",
                "vaseline-business-simple-search-layerimpl-api",
                "vaseline-business-advanced-search-layer-api",
                "vaseline-business-advanced-search-layerimpl-api"
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
                "vaseline-business-basic-layer-api",
                "vaseline-business-basic-layerimpl-api",
                "vaseline-business-multidao-layer-api",
                "vaseline-business-multidao-layerimpl-api"
        };
    }
}
