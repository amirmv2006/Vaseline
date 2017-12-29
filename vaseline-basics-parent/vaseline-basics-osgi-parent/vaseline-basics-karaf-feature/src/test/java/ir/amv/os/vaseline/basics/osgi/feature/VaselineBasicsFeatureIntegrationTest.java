package ir.amv.os.vaseline.basics.osgi.feature;

import ir.amv.os.vaseline.basics.apis.core.server.polymorphysm.IVaselinePolymorphysmClassHolder;
import ir.amv.os.vaseline.basics.apis.json.server.converter.IVaselineJsonConverter;
import ir.amv.os.vaseline.basics.apis.logging.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.osgi.testing.util.BundleServiceChecker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;
import org.ops4j.pax.exam.options.MavenArtifactUrlReference;
import org.ops4j.pax.exam.options.MavenUrlReference;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.configureConsole;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.debugConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.keepRuntimeFolder;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel;

/**
 * @author Amir
 */
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class VaselineBasicsFeatureIntegrationTest {

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

    @Inject
    protected BundleContext bundleContext;

    @Configuration
    public Option[] config() {
        MavenArtifactUrlReference karafUrl = maven()
                .groupId(getKarafGroupId())
                .artifactId(getKarafArtifactId())
                .version(getKarafVersion())
                .type("zip");

        MavenUrlReference karafStandardRepo = maven()
                .groupId(getFeaturesGroupId())
                .artifactId(getFeaturesArtifactId())
                .version(getFeaturesVersion())
                .classifier("features")
                .type("xml");
        File unpackDirectory = new File("target", "exam");
        return new Option[]{
                // KarafDistributionOption.debugConfiguration("5005", true),
                karafDistributionConfiguration()
                        .frameworkUrl(karafUrl)
                        .unpackDirectory(unpackDirectory)
                        .useDeployFolder(false),
                keepRuntimeFolder(),
                configureConsole().ignoreLocalConsole(),
                mavenBundle()
                        .groupId("javax.validation")
                        .artifactId("validation-api")
                        .version("2.0.0.Final")
                        .start(),
                mavenBundle()
                        .groupId("org.apache.geronimo.specs")
                        .artifactId("geronimo-jpa_2.1_spec")
                        .version("1.0-alpha-1")
                        .start(),
                mavenBundle()
                        .groupId("com.github.amirmv2006.basics.osgi")
                        .artifactId("vaseline-basics-ogsi-testing-util")
                        .version("1.0-SNAPSHOT")
                        .start(),
                features(karafStandardRepo, getTestFeatures()),
                logLevel(LogLevelOption.LogLevel.INFO),
                debugConfiguration("5555", false)
        };
    }

    protected String[] getTestFeatures() {
        return new String[]{
              getFeaturesArtifactId()
        };
    }

    protected String getFeaturesRepositoryUrl() {
        return "mvn:" + getFeaturesGroupId() + "/" + getFeaturesArtifactId() + "/" + getFeaturesVersion() + "/xml/features";
    }

    protected String getFeaturesVersion() {
        return System.getProperty("vasline.basics.features.version", "1.0-SNAPSHOT");
    }

    protected String getFeaturesArtifactId() {
        return System.getProperty("vasline.basics.features.artifactId", "vaseline-basics-karaf-feature");
    }

    protected String getFeaturesGroupId() {
        return System.getProperty("vasline.basics.features.groupId", "com.github.amirmv2006.basics.osgi");
    }

    protected String getKarafVersion() {
        return System.getProperty("org.apache.karaf.version", "4.1.2");
    }

    protected String getKarafArtifactId() {
        return System.getProperty("org.apache.karaf.artifactId", "apache-karaf");
    }

    protected String getKarafGroupId() {
        return System.getProperty("org.apache.karaf.groupId", "org.apache.karaf");
    }
}
