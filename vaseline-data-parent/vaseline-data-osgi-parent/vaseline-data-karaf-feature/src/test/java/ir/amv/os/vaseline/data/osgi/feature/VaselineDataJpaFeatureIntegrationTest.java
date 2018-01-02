package ir.amv.os.vaseline.data.osgi.feature;

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
public class VaselineDataJpaFeatureIntegrationTest
        extends AbstractVaselineDataIntegrationTest {

    @Test
    public void testBundles() throws InvalidSyntaxException, ClassNotFoundException, InterruptedException {
        assertNotNull(bundleContext);
        List<AbstractBundleChecker> bundleCheckers = new ArrayList<>();
        bundleCheckers.add(new BundleStartedChecker("vaseline-basic-dao-api", 20000));
        bundleCheckers.add(new BundleStartedChecker("vaseline-simple-search-dao-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-advanced-search-dao-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-jpa-basic-dao-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-jpa-simple-search-dao-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-jpa-advanced-search-dao-api"));

        for (AbstractBundleChecker bundleChecker : bundleCheckers) {
            bundleChecker.checkBundle(bundleContext);
        }
    }

    @Override
    protected String[] getTestFeatures() {
        return new String[]{
                "vaseline-data-karaf-feature-jpa"
        };
    }
}
