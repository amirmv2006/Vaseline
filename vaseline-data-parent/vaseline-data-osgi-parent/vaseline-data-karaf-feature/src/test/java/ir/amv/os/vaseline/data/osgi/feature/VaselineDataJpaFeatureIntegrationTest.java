package ir.amv.os.vaseline.data.osgi.feature;

import ir.amv.os.vaseline.basics.testing.osgi.util.AbstractBundleChecker;
import ir.amv.os.vaseline.basics.testing.osgi.util.BundleStartedChecker;
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
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.data.dao.basic.api", 20000));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.data.search.simple.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.data.search.advanced.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.data.jpa.dao.basic.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.data.jpa.search.simple.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.data.jpa.search.advanced.api"));

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
