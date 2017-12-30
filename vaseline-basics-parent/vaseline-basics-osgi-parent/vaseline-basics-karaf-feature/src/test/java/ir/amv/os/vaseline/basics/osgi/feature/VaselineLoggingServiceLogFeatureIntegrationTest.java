package ir.amv.os.vaseline.basics.osgi.feature;

import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.osgi.testing.util.BundleServiceChecker;
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
    public void testGsonServiceRegister() throws InvalidSyntaxException, ClassNotFoundException, InterruptedException {
        assertNotNull(bundleContext);
        List<BundleServiceChecker> serviceCheckers = new ArrayList<>();
        serviceCheckers.add(new BundleServiceChecker("vaseline-basics-logging-log-service-osgi",
                Collections.singletonList(IVaselineLogger.class), 5000));

        for (BundleServiceChecker serviceChecker : serviceCheckers) {
            serviceChecker.checkForRegisteredServices(bundleContext);
        }
    }

    @Override
    protected String[] getTestFeatures() {
        return new String[]{
                "vaseline-basics-logging-log-service"
        };
    }
}
