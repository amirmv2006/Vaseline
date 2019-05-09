package ir.amv.os.vaseline.business.osgi.feature.basic;

import cucumber.api.java.en.And;
import ir.amv.os.vaseline.basics.core.osgi.constants.VaselineCoreOsgiConstants;
import ir.amv.os.vaseline.basics.karaf.feature.helper.VaselineBasicsHelper;
import ir.amv.os.vaseline.business.osgi.feature.basic.layer.ISampleBasicApi;
import ir.amv.os.vaseline.business.osgi.feature.basic.layer.SampleBasicApiImpl;
import ir.amv.os.vaseline.data.osgi.feature.basic.dao.ISampleBasicDao;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RequireClassRemotely;
import org.osgi.framework.BundleContext;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RequireClassRemotely({ISampleBasicApi.class, SampleBasicApiImpl.class})
public class BasicApiRegister {

    @Inject
    private ISampleBasicDao sampleDao;
    @Inject
    private BundleContext bundleContext;

    @And("register Sample Basic Business")
    public void registerSampleBasicBusiness() {
        SampleBasicApiImpl sampleApi = new SampleBasicApiImpl(sampleDao);
        Map<String, Object> map = new HashMap<>();
        map.put(VaselineCoreOsgiConstants.NEEDS_PROXY, true);
        VaselineBasicsHelper.registerService(bundleContext, ISampleBasicApi.class, sampleApi, map);
        assertNotNull(sampleApi.getProxy(ISampleBasicApi.class));
    }
}
