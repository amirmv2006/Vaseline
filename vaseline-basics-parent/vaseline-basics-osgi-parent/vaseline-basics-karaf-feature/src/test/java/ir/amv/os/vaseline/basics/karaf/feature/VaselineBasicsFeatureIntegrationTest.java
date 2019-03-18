package ir.amv.os.vaseline.basics.karaf.feature;

import ir.amv.os.vaseline.basics.cache.api.server.IVaselineCacheApi;
import ir.amv.os.vaseline.basics.cache.api.server.IVaselineCacheConfigurer;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.IBaseExceptionConverter;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.core.api.server.polymorphysm.IVaselinePolymorphysmClassHolder;
import ir.amv.os.vaseline.basics.i18n.api.server.file.resolver.IVaselineI18nFileProvider;
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.IVaselineMessageTranslator;
import ir.amv.os.vaseline.basics.json.api.server.converter.IVaselineJsonConverter;
import ir.amv.os.vaseline.basics.logging.api.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.mapper.api.server.objmapper.IVaselineObjectMapper;
import ir.amv.os.vaseline.basics.testing.osgi.util.AbstractBundleChecker;
import ir.amv.os.vaseline.basics.testing.osgi.util.BundleServiceChecker;
import ir.amv.os.vaseline.basics.testing.osgi.util.BundleStartedChecker;
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
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.thirdparty.shared.util.reflection"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.basics.i18n.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.basics.jdbc.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.basics.json.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.basics.logging.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.basics.logging.def"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.basics.core.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.basics.dao.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.basics.mapper.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.basics.validation.api"));
        bundleCheckers.add(new BundleStartedChecker("ir.amv.os.vaseline.basics.base.osgi"));
        bundleCheckers.add(new BundleServiceChecker("ir.amv.os.vaseline.basics.cache.hazelcast.osgi",
                Arrays.asList(IVaselineCacheApi.class, IVaselineCacheConfigurer.class)));
        bundleCheckers.add(new BundleServiceChecker("ir.amv.os.vaseline.basics.core.osgi",
                Arrays.asList(ICoreExceptionHandler.class, IBaseExceptionConverter.class)));
        bundleCheckers.add(new BundleServiceChecker("ir.amv.os.vaseline.basics.i18n.osgi",
                Arrays.asList(IVaselineMessageTranslator.class, IVaselineI18nFileProvider.class)));
        bundleCheckers.add(new BundleServiceChecker("ir.amv.os.vaseline.basics.validation.hibval.osgi",
                Collections.singletonList(Validator.class)));
        bundleCheckers.add(new BundleServiceChecker("ir.amv.os.vaseline.basics.mapper.dozer.osgi",
                Collections.singletonList(IVaselineObjectMapper.class)));
        bundleCheckers.add(new BundleServiceChecker("ir.amv.os.vaseline.basics.logging.pax.osgi",
                Collections.singletonList(IVaselineLogger.class)));
        bundleCheckers.add(new BundleServiceChecker("ir.amv.os.vaseline.basics.logging.common.osgi",
                Collections.singletonList(IVaselineLogCategorizer.class), 5000));
        bundleCheckers.add(new BundleServiceChecker("ir.amv.os.vaseline.basics.json.gson.osgi",
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
