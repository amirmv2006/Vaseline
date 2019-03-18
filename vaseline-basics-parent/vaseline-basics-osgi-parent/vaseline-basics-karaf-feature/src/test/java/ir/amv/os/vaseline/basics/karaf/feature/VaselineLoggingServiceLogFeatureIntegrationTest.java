package ir.amv.os.vaseline.basics.karaf.feature;

import ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.testing.osgi.util.BundleServiceChecker;
import org.junit.Test;
import org.osgi.framework.InvalidSyntaxException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Amir
 */
public class VaselineLoggingServiceLogFeatureIntegrationTest extends AbstractVaselineBasicsIntegrationTest {

    @Test
    public void testLoggingServiceFeature() throws InvalidSyntaxException, ClassNotFoundException,
            InterruptedException {
        assertNotNull(bundleContext);
        List<BundleServiceChecker> serviceCheckers = new ArrayList<>();
        serviceCheckers.add(new BundleServiceChecker("ir.amv.os.vaseline.basics.logging.loggingservice.osgi",
                Collections.singletonList(IVaselineLogger.class), 5000));

        for (BundleServiceChecker serviceChecker : serviceCheckers) {
            serviceChecker.checkBundle(bundleContext);
        }
    }

    @Override
    protected String[] getTestFeatures() {
        return new String[]{
                "vaseline-basics-logging-log-service",
                "vaseline-basics-logging-common"
        };
    }
}
