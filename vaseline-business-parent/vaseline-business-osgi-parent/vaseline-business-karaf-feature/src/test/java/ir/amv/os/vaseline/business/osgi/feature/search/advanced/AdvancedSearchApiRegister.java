package ir.amv.os.vaseline.business.osgi.feature.search.advanced;

import cucumber.api.java.en.And;
import ir.amv.os.vaseline.basics.core.osgi.constants.VaselineCoreOsgiConstants;
import ir.amv.os.vaseline.basics.karaf.feature.helper.VaselineBasicsHelper;
import ir.amv.os.vaseline.business.osgi.feature.search.advanced.layer.ISampleAdvancedSearchApi;
import ir.amv.os.vaseline.business.osgi.feature.search.advanced.layer.SampleAdvancedSearchApi;
import ir.amv.os.vaseline.data.osgi.feature.search.advanced.dao.ISampleAdvancedSearchDao;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RequireClassRemotely;
import org.osgi.framework.BundleContext;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RequireClassRemotely({ISampleAdvancedSearchApi.class, SampleAdvancedSearchApi.class})
public class AdvancedSearchApiRegister {
    @Inject
    ISampleAdvancedSearchDao dao;
    @Inject
    BundleContext bundleContext;

    @And("register Sample AdvancedSearch Business")
    public void registerSampleSimpleSearchBusiness() {
        SampleAdvancedSearchApi sampleApi = new SampleAdvancedSearchApi(dao);
        Map<String, Object> map = new HashMap<>();
        map.put(VaselineCoreOsgiConstants.NEEDS_PROXY, true);
        VaselineBasicsHelper.registerService(bundleContext, ISampleAdvancedSearchApi.class, sampleApi, map);
        assertNotNull(sampleApi.getProxy(ISampleAdvancedSearchApi.class));

    }

}
