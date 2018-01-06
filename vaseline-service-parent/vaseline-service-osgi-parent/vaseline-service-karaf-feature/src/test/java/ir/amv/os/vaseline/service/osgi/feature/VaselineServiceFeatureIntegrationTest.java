package ir.amv.os.vaseline.service.osgi.feature;

import ir.amv.os.vaseline.basics.osgi.testing.util.AbstractBundleChecker;
import ir.amv.os.vaseline.basics.osgi.testing.util.BundleStartedChecker;
import org.junit.Test;
import org.osgi.framework.InvalidSyntaxException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Amir
 */
public class VaselineServiceFeatureIntegrationTest
        extends AbstractVaselineServiceIntegrationTest {

    @Test
    public void testBundles() throws InvalidSyntaxException, ClassNotFoundException, InterruptedException {
        assertNotNull(bundleContext);
        List<AbstractBundleChecker> bundleCheckers = new ArrayList<>();
        bundleCheckers.add(new BundleStartedChecker("vaseline-service-basic-layer-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-service-basic-layerimpl-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-service-simple-search-layer-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-service-simple-search-layerimpl-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-service-advanced-search-layer-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-service-advanced-search-layerimpl-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-service-multidao-layer-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-service-multidao-layerimpl-api"));

        for (AbstractBundleChecker bundleChecker : bundleCheckers) {
            bundleChecker.checkBundle(bundleContext);
        }
    }

    @Override
    protected String[] getTestFeatures() {
        return new String[]{
                "vaseline-service-karaf-feature"
        };
    }
}
