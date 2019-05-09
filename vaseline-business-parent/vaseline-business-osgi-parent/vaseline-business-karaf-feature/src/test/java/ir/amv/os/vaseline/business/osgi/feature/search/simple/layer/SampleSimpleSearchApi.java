package ir.amv.os.vaseline.business.osgi.feature.search.simple.layer;

import ir.amv.os.vaseline.basics.core.api.server.proxy.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.business.search.simple.def.server.IDefaultSimpleSearchApi;
import ir.amv.os.vaseline.data.osgi.feature.search.simple.dao.ISampleSimpleSearchDao;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleDto;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;

public class SampleSimpleSearchApi
        extends ProxyAwareImpl
        implements IDefaultSimpleSearchApi<SampleEntity, SampleDto, Long, ISampleSimpleSearchDao>,
        ISampleSimpleSearchApi{
    private ISampleSimpleSearchDao dao;

    public SampleSimpleSearchApi(ISampleSimpleSearchDao dao) {
        this.dao = dao;
    }

    @Override
    public ISampleSimpleSearchDao getDao() {
        return dao;
    }
}
