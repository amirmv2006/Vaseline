package ir.amv.os.vaseline.business.osgi.feature;

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
public class VaselineBusinessFeatureIntegrationTest
        extends AbstractVaselineBusinessIntegrationTest {

    @Test
    public void testBundles() throws InvalidSyntaxException, ClassNotFoundException, InterruptedException {
        assertNotNull(bundleContext);
        List<AbstractBundleChecker> bundleCheckers = new ArrayList<>();
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.business.basic.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.business.basic.def"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.business.search.simple.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.business.search.simple.def"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.business.search.advanced.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.business.search.advanced.def"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.business.multidao.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.business.multidao.def"));

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
