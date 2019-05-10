package ir.amv.os.vaseline.business.osgi.feature.search.advanced.layer;

import ir.amv.os.vaseline.basics.core.api.server.proxy.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.business.search.advanced.def.server.IDefaultAdvancedSearchApi;
import ir.amv.os.vaseline.data.osgi.feature.search.advanced.dao.ISampleAdvancedSearchDao;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleSearchObject;

public class SampleAdvancedSearchApi
        extends ProxyAwareImpl
        implements IDefaultAdvancedSearchApi<Long, SampleEntity, SampleSearchObject, ISampleAdvancedSearchDao>,
        ISampleAdvancedSearchApi{
    private ISampleAdvancedSearchDao dao;

    public SampleAdvancedSearchApi(ISampleAdvancedSearchDao dao) {
        this.dao = dao;
    }

    @Override
    public ISampleAdvancedSearchDao getDao() {
        return dao;
    }
}
