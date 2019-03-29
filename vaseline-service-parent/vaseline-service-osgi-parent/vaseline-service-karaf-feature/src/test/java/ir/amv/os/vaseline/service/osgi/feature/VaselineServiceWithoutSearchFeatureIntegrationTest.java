package ir.amv.os.vaseline.service.osgi.feature;

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
public class VaselineServiceWithoutSearchFeatureIntegrationTest
        extends AbstractVaselineServiceIntegrationTest {

    @Test
    public void testBundles() throws InvalidSyntaxException, ClassNotFoundException, InterruptedException {
        assertNotNull(bundleContext);
        List<AbstractBundleChecker> bundleCheckers = new ArrayList<>();
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.service.basic.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.service.basic.def"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.service.multidao.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.service.multidao.def"));

        for (AbstractBundleChecker bundleChecker : bundleCheckers) {
            bundleChecker.checkBundle(bundleContext);
        }
        Bundle[] bundles = bundleContext.getBundles();
        List<String> bannedBundles = Arrays.asList(
                "ir.amv.os.vaseline.service.search.simple.api",
                "ir.amv.os.vaseline.service.search.simple.def",
                "ir.amv.os.vaseline.service.search.advanced.api",
                "ir.amv.os.vaseline.service.search.advanced.def"
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
                "ir.amv.os.vaseline.service.basic.api",
                "ir.amv.os.vaseline.service.basic.def",
                "ir.amv.os.vaseline.service.multidao.api",
                "ir.amv.os.vaseline.service.multidao.def"
        };
    }
}
