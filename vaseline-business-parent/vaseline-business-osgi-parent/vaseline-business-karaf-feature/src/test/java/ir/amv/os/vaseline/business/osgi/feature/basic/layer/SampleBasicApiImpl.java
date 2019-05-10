package ir.amv.os.vaseline.business.osgi.feature.basic.layer;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.server.proxy.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.business.basic.def.server.crud.IDefaultCrudApi;
import ir.amv.os.vaseline.data.osgi.feature.basic.dao.ISampleBasicDao;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;

import javax.transaction.Transactional;

public class SampleBasicApiImpl
        extends ProxyAwareImpl
        implements IDefaultCrudApi<Long, SampleEntity, ISampleBasicDao>,
        ISampleBasicApi {

    private ISampleBasicDao dao;

    public SampleBasicApiImpl(ISampleBasicDao dao) {
        this.dao = dao;
    }

    @Override
    public ISampleBasicDao getDao() {
        return dao;
    }

    @Override
    @Transactional
    public Long save(SampleEntity entity) throws BaseVaselineServerException {
        return IDefaultCrudApi.super.save(entity);
    }
}
