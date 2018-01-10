package ir.amv.os.vaseline.basics.osgi.feature;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.IBaseExceptionConverter;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.apis.core.server.polymorphysm.IVaselinePolymorphysmClassHolder;
import ir.amv.os.vaseline.basics.apis.i18n.server.file.resolver.IVaselineI18nFileProvider;
import ir.amv.os.vaseline.basics.apis.i18n.server.message.translator.IVaselineMessageTranslator;
import ir.amv.os.vaseline.basics.apis.json.server.converter.IVaselineJsonConverter;
import ir.amv.os.vaseline.basics.apis.logging.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.apis.mapper.server.objmapper.IVaselineObjectMapper;
import ir.amv.os.vaseline.basics.osgi.testing.util.AbstractBundleChecker;
import ir.amv.os.vaseline.basics.osgi.testing.util.BundleServiceChecker;
import ir.amv.os.vaseline.basics.osgi.testing.util.BundleStartedChecker;
import org.junit.Test;
import org.osgi.framework.InvalidSyntaxException;

import javax.validation.Validator;
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
    public void testBasicFeature() throws InvalidSyntaxException, ClassNotFoundException, InterruptedException {
        assertNotNull(bundleContext);
        List<AbstractBundleChecker> bundleCheckers = new ArrayList<>();
        bundleCheckers.add(new BundleStartedChecker("vaseline-reflection-util"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-i18n-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-jdbc-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-json-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-basics-logging-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-basics-loggingimpl-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-core-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-entity-jpa-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-mapper-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-validation-api"));
        bundleCheckers.add(new BundleStartedChecker("vaseline-basics-base-bundle-osgi"));
        bundleCheckers.add(new BundleServiceChecker("vaseline-basics-core-osgi",
                Arrays.asList(ICoreExceptionHandler.class, IBaseExceptionConverter.class)));
        bundleCheckers.add(new BundleServiceChecker("vaseline-basics-i18n-osgi",
                Arrays.asList(IVaselineMessageTranslator.class, IVaselineI18nFileProvider.class)));
        bundleCheckers.add(new BundleServiceChecker("vaseline-basics-validation-hibernate-validator-osgi",
                Collections.singletonList(Validator.class)));
        bundleCheckers.add(new BundleServiceChecker("vaseline-basics-mapper-dozer-osgi",
                Collections.singletonList(IVaselineObjectMapper.class)));
        bundleCheckers.add(new BundleServiceChecker("vaseline-basics-logging-pax-logging-osgi",
                Collections.singletonList(IVaselineLogger.class)));
        bundleCheckers.add(new BundleServiceChecker("vaseline-basics-logging-common-osgi",
                Collections.singletonList(IVaselineLogCategorizer.class), 5000));
        bundleCheckers.add(new BundleServiceChecker("vaseline-basics-json-gson-osgi",
                Arrays.asList(IVaselinePolymorphysmClassHolder.class, IVaselineJsonConverter.class), 5000, 3));

        for (AbstractBundleChecker bundleChecker : bundleCheckers) {
            bundleChecker.checkBundle(bundleContext);
        }
    }

    protected String[] getTestFeatures() {
        return new String[]{
                getFeaturesArtifactId()
        };
    }

}
