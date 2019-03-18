package ir.amv.os.vaseline.data.osgi.feature;

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

import javax.inject.Inject;
import java.io.File;

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
public abstract class AbstractVaselineDataIntegrationTest {

    @Inject
    protected BundleContext bundleContext;

    protected abstract String[] getTestFeatures();

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
                        .groupId("org.apache.geronimo.specs")
                        .artifactId("geronimo-jpa_2.1_spec")
                        .version("1.0-alpha-1")
                        .start(),
                mavenBundle()
                        .groupId("com.github.amirmv2006.basics.osgi")
                        .artifactId("ir.amv.os.vaseline.basics.testing.osgi")
                        .version("1.0-SNAPSHOT")
                        .start(),
                features(karafStandardRepo, getTestFeatures()),
                logLevel(LogLevelOption.LogLevel.INFO),
                debugConfiguration("5555", false)
        };
    }

    protected String getFeaturesVersion() {
        return System.getProperty("vasline.feature.under.test.version", "1.0-SNAPSHOT");
    }

    protected String getFeaturesArtifactId() {
        return System.getProperty("vasline.feature.under.test.artifactId", "vaseline-data-karaf-feature");
    }

    protected String getFeaturesGroupId() {
        return System.getProperty("vasline.feature.under.test.groupId", "com.github.amirmv2006.data.osgi");
    }

    protected String getKarafVersion() {
        return System.getProperty("org.apache.karaf.version", "4.2.3");
    }

    protected String getKarafArtifactId() {
        return System.getProperty("org.apache.karaf.artifactId", "apache-karaf");
    }

    protected String getKarafGroupId() {
        return System.getProperty("org.apache.karaf.groupId", "org.apache.karaf");
    }

}
