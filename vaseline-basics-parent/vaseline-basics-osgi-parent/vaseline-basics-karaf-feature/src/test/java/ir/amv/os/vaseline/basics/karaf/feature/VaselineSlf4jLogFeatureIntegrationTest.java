package ir.amv.os.vaseline.basics.karaf.feature;

import ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.testing.integration.bundle.checker.BundleServiceChecker;
import ir.amv.os.vaseline.thirdparty.it.karaf.helper.AbstractVaselineKarafIntegrationTest;
import org.junit.Test;
import org.osgi.framework.InvalidSyntaxException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.debugConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel;

/**
 * @author Amir
 */
public class VaselineSlf4jLogFeatureIntegrationTest extends AbstractVaselineKarafIntegrationTest {

    @Test
    public void testSlf4jFeature() throws InvalidSyntaxException, ClassNotFoundException, InterruptedException {
        assertNotNull(bundleContext);
        List<BundleServiceChecker> serviceCheckers = new ArrayList<>();
        serviceCheckers.add(new BundleServiceChecker("ir.amv.os.vaseline.basics.logging.slf4j.osgi",
                Collections.singletonList(IVaselineLogger.class), 5000));

        for (BundleServiceChecker serviceChecker : serviceCheckers) {
            serviceChecker.checkBundle(bundleContext);
        }
    }

    @Override
    protected String[] getTestFeatures() {
        return new String[]{
                "vaseline-logger-slf4j",
                "vaseline-basics-logging-common"
        };
    }
}
