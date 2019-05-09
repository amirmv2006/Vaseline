package ir.amv.os.vaseline.basics.karaf.feature.proxy;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import ir.amv.os.vaseline.basics.core.api.server.proxy.IProxyInterceptor;
import ir.amv.os.vaseline.basics.core.osgi.constants.VaselineCoreOsgiConstants;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RequireClassRemotely;
import org.osgi.framework.BundleContext;

import javax.inject.Inject;
import java.util.Hashtable;

import static org.junit.Assert.assertTrue;

@RequireClassRemotely({ProxyInterceptor.class, ISampleOsgiService.class, SampleOsgiServiceImpl.class})
public class MyStepdefs {

    @Inject
    private BundleContext bundleContext;
    private ProxyInterceptor proxyInterceptor;

    @And("I register Sample Proxy Interceptor")
    public void iRegisterSampleProxyInterceptor() {
        proxyInterceptor = new ProxyInterceptor();
        bundleContext.registerService(IProxyInterceptor.class, proxyInterceptor, new Hashtable<>());
    }

    @And("I regsiter a service which needs proxy")
    public void iRegsiterAServiceWichNeedsProxy() {
        Hashtable<String, Object> properties = new Hashtable<>();
        properties.put(VaselineCoreOsgiConstants.NEEDS_PROXY, true);
        bundleContext.registerService(ISampleOsgiService.class, new SampleOsgiServiceImpl(), properties);
    }

    @Then("Sample Proxy Interceptor is called")
    public void sampleProxyInterceptorIsCalled() {
        assertTrue(proxyInterceptor.isPreExecuteCalled());
        assertTrue(proxyInterceptor.isPostExecuteSuccessCalled());
    }
}
