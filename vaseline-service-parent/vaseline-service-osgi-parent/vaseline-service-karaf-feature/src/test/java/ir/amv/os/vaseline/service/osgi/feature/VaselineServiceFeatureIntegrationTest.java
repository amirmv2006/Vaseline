package ir.amv.os.vaseline.service.osgi.feature;

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
public class VaselineServiceFeatureIntegrationTest
        extends AbstractVaselineServiceIntegrationTest {

    @Test
    public void testBundles() throws InvalidSyntaxException, ClassNotFoundException, InterruptedException {
        assertNotNull(bundleContext);
        List<AbstractBundleChecker> bundleCheckers = new ArrayList<>();
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.service.basic.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.service.basic.def"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.service.search.simple.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.service.search.simple.def"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.service.search.advanced.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.service.search.advanced.def"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.service.multidao.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.service.multidao.def"));

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
