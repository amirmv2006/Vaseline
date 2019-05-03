package ir.amv.os.vaseline.testing.integration.cucumber.karaf.helper;

import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;
import org.ops4j.pax.exam.options.MavenArtifactUrlReference;
import org.ops4j.pax.exam.options.MavenUrlReference;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.*;

public class KarafOptionsHelper {

    public static List<Option> defaultOptions() {
        MavenArtifactUrlReference karafUrl = maven()
                .groupId("org.apache.karaf")
                .artifactId("apache-karaf")
                .versionAsInProject()
                .type("zip");

        File unpackDirectory = new File("target", "exam");
        return Arrays.asList(
                karafDistributionConfiguration()
                        .frameworkUrl(karafUrl)
                        .unpackDirectory(unpackDirectory)
                        .useDeployFolder(false),
                keepRuntimeFolder(),
                configureConsole().ignoreLocalConsole(),
                logLevel(LogLevelOption.LogLevel.INFO),
                debugConfiguration("5555", false)
        );
    }

    public static Option deployFeature(String featureGroupId, String featuresArtifactId, String featureNames) {
        String[] features;
        if (featureNames.contains(",")) {
            features = featureNames.split(",");
        } else {
            features = new String[]{featureNames};
        }
        MavenUrlReference karafStandardRepo = maven()
                .groupId(featureGroupId)
                .artifactId(featuresArtifactId)
                .versionAsInProject()
                .classifier("features")
                .type("xml");
        return features(karafStandardRepo, features);
    }

    public static Option mavenBundleVersionAsInProject(String groupId, String artifactId) {
        return mavenBundle()
                .groupId(groupId)
                .artifactId(artifactId)
                .versionAsInProject()
                .start();
    }

}
