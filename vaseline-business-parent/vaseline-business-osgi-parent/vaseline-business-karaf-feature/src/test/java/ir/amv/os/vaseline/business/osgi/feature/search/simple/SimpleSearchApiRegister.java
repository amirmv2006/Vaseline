package ir.amv.os.vaseline.business.osgi.feature.search.simple;

import cucumber.api.java.en.And;
import ir.amv.os.vaseline.basics.core.osgi.constants.VaselineCoreOsgiConstants;
import ir.amv.os.vaseline.basics.karaf.feature.helper.VaselineBasicsHelper;
import ir.amv.os.vaseline.business.osgi.feature.search.simple.layer.ISampleSimpleSearchApi;
import ir.amv.os.vaseline.business.osgi.feature.search.simple.layer.SampleSimpleSearchApi;
import ir.amv.os.vaseline.data.osgi.feature.search.simple.dao.ISampleSimpleSearchDao;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RequireClassRemotely;
import org.osgi.framework.BundleContext;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RequireClassRemotely({ISampleSimpleSearchApi.class, SampleSimpleSearchApi.class})
public class SimpleSearchApiRegister {

    @Inject
    ISampleSimpleSearchDao dao;
    @Inject
    BundleContext bundleContext;

    @And("register Sample SimpleSearch Business")
    public void registerSampleSimpleSearchBusiness() {
        SampleSimpleSearchApi sampleApi = new SampleSimpleSearchApi(dao);
        Map<String, Object> map = new HashMap<>();
        map.put(VaselineCoreOsgiConstants.NEEDS_PROXY, true);
        VaselineBasicsHelper.registerService(bundleContext, ISampleSimpleSearchApi.class, sampleApi, map);
        assertNotNull(sampleApi.getProxy(ISampleSimpleSearchApi.class));

    }

}
