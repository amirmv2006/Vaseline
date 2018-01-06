package ir.amv.os.vaseline.business.osgi.feature;

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
public class VaselineBusinessFeatureIntegrationTest
        extends AbstractVaselineBusinessIntegrationTest {

    @Test
    public void testBundles() throws InvalidSyntaxException, ClassNotFoundException, InterruptedException {
        assertNotNull(bundleContext);
        List<AbstractBundleChecker> bundleCheckers = new ArrayList<>();
        bundleCheckers.add(new BundleStartedChecker("vaseline-business-basic-layer-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-business-basic-layerimpl-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-business-simple-search-layer-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-business-simple-search-layerimpl-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-business-advanced-search-layer-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-business-advanced-search-layerimpl-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-business-multidao-layer-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-business-multidao-layerimpl-api"));

        for (AbstractBundleChecker bundleChecker : bundleCheckers) {
            bundleChecker.checkBundle(bundleContext);
        }
    }

    @Override
    protected String[] getTestFeatures() {
        return new String[]{
                "vaseline-business-karaf-feature"
        };
    }
}
