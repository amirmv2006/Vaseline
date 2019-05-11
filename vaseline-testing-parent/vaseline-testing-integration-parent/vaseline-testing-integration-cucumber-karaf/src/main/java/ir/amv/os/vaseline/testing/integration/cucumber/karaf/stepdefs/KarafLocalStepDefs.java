package ir.amv.os.vaseline.testing.integration.cucumber.karaf.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RegisterService;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RemoteObjectFactory;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RunLocally;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.listener.IRemoteObjectListener;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.lang.reflect.Method;

import static ir.amv.os.vaseline.testing.integration.cucumber.karaf.helper.KarafOptionsHelper.*;

public class KarafLocalStepDefs {
    @Given("^I have karaf$")
    @RunLocally
    public void iHaveKaraf() {
        RemoteObjectFactory.getInstance().getRemoteKarafEnvironment().addOptions(defaultOptions());
    }

    @Given("^start bundle with groupId=\"([^\"]*)\" and artifactId=\"([^\"]*)\" and version as in project$")
    @RunLocally
    public void mavenBundleWithVersionAsInProject(String groupId, String artifactId) {
        RemoteObjectFactory.getInstance().getRemoteKarafEnvironment().addOption(
                mavenBundleVersionAsInProject(groupId, artifactId)
        );
    }

    @Given("^feature with groupId=\"([^\"]*)\", artifactId=\"([^\"]*)\" and name=\"([^\"]*)\" is deployed")
    @RunLocally
    public void installFeature(String featureGroupId, String featureArtifactId, String featureNames) {
        RemoteObjectFactory.getInstance().getRemoteKarafEnvironment().addOption(
                deployFeature(featureGroupId, featureArtifactId, featureNames)
        );
    }


    @When("^start karaf$")
    @RunLocally
    public void runKaraf() {
        RemoteObjectFactory.getInstance().getRemoteKarafEnvironment().startKaraf();
        KarafRemoteStepDefs remoteInstance = RemoteObjectFactory.getInstance().getInstance(KarafRemoteStepDefs.class);
        RemoteObjectFactory.getInstance().getRemoteKarafEnvironment().addRemoteObjectListener(
                new IRemoteObjectListener() {
                    @Override
                    public <C> void remoteObjectCreated(Class<C> objectClass, C object) {
                        RegisterService registerService = ReflectionUtil.getAnnotationInHierarchy(objectClass, RegisterService.class);
                        if (registerService != null) {
                            try {
                                System.out.println("Registering service for " + registerService.interfaceClass());
                                remoteInstance.registerServiceFor(objectClass.getName());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void remoteMethodInvoked(Object proxy, Method method, Object[] args) {
                        // do nothing
                    }
                }
        );
    }
}
