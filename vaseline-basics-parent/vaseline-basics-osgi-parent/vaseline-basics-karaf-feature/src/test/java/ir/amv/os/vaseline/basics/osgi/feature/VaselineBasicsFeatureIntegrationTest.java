package ir.amv.os.vaseline.basics.osgi.feature;

import ir.amv.os.vaseline.basics.apis.core.server.polymorphysm.IVaselinePolymorphysmClassHolder;
import ir.amv.os.vaseline.basics.apis.json.server.converter.IVaselineJsonConverter;
import ir.amv.os.vaseline.basics.apis.logging.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.osgi.testing.util.BundleServiceChecker;
import org.junit.Test;
import org.osgi.framework.InvalidSyntaxException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Amir
 */
public class VaselineBasicsFeatureIntegrationTest extends AbstractVaselineBasicsIntegrationTest {

    @Test
    public void testGsonServiceRegister() throws InvalidSyntaxException, ClassNotFoundException, InterruptedException {
        assertNotNull(bundleContext);
        List<BundleServiceChecker> serviceCheckers = new ArrayList<>();
        serviceCheckers.add(new BundleServiceChecker("vaseline-basics-logging-pax-logging-osgi",
                Collections.singletonList(IVaselineLogger.class), 5000));
        serviceCheckers.add(new BundleServiceChecker("vaseline-basics-logging-common-osgi",
                Collections.singletonList(IVaselineLogCategorizer.class), 5000));
        serviceCheckers.add(new BundleServiceChecker("vaseline-basics-json-gson-osgi",
                Arrays.asList(IVaselinePolymorphysmClassHolder.class, IVaselineJsonConverter.class), 5000));

        for (BundleServiceChecker serviceChecker : serviceCheckers) {
            serviceChecker.checkForRegisteredServices(bundleContext);
        }
    }

    protected String[] getTestFeatures() {
        return new String[]{
              getFeaturesArtifactId()
        };
    }

}
