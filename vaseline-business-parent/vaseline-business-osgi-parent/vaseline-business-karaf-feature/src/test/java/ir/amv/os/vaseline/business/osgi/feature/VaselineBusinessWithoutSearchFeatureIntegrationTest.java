package ir.amv.os.vaseline.business.osgi.feature;

import ir.amv.os.vaseline.testing.integration.bundle.checker.AbstractBundleChecker;
import ir.amv.os.vaseline.testing.integration.bundle.checker.BundleStartedChecker;
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
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.business.basic.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.business.basic.def"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.business.multidao.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.business.multidao.def"));

        for (AbstractBundleChecker bundleChecker : bundleCheckers) {
            bundleChecker.checkBundle(bundleContext);
        }
        Bundle[] bundles = bundleContext.getBundles();
        List<String> bannedBundles = Arrays.asList(
                "ir.amv.os.vaseline.business.search.simple.api",
                "ir.amv.os.vaseline.business.search.simple.def",
                "ir.amv.os.vaseline.business.search.advanced.api",
                "ir.amv.os.vaseline.business.search.advanced.def"
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
                "ir.amv.os.vaseline.business.basic.api",
                "ir.amv.os.vaseline.business.basic.def",
                "ir.amv.os.vaseline.business.multidao.api",
                "ir.amv.os.vaseline.business.multidao.def"
        };
    }
}
