package ir.amv.os.vaseline.base.architecture.impl.server.layers.multidao.ro.api;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.ro.api.BaseEntityReadOnlyApiImpl;
import ir.amv.os.vaseline.data.apis.dao.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.base.architecture.server.layers.multidao.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public abstract class BaseMultiDaoReadOnlyApiImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, DAO extends IBaseReadOnlyDao<E, Id>>
        extends BaseEntityReadOnlyApiImpl<E, Id>
        implements IBaseMultiDaoReadOnlyApi<E, D, Id> {

    protected List<DAO> daoList;

    @Override
    @Transactional(readOnly =  true)
    public E getById(String coreId, Id id) throws BaseVaselineServerException {
        return BaseMultiDaoReadOnlyApiImplHelper.getById(this, coreId, id);
    }

    @Override
    @Transactional(readOnly =  true)
    public Long countAll(String coreId) throws BaseVaselineServerException {
        return BaseMultiDaoReadOnlyApiImplHelper.countAll(this, coreId);
    }

    @Override
    @Transactional(readOnly =  true)
    public List<E> getAll(String coreId) throws BaseVaselineServerException {
        return BaseMultiDaoReadOnlyApiImplHelper.getAll(this, coreId);
    }

    @Override
    @Transactional(readOnly =  true)
    public List<E> getAll(String coreId, PagingDto pagingDto) throws BaseVaselineServerException {
        return BaseMultiDaoReadOnlyApiImplHelper.getAll(this, coreId, pagingDto);
    }

    @Override
    @Transactional(readOnly =  true)
    public Long countByExample(String coreId, D example) throws BaseVaselineServerException {
        return BaseMultiDaoReadOnlyApiImplHelper.countByExample(this, coreId, example);
    }

    @Override
    @Transactional(readOnly =  true)
    public List<E> searchByExample(String coreId, D example) throws BaseVaselineServerException {
        return BaseMultiDaoReadOnlyApiImplHelper.searchByExample(this, coreId, example);
    }

    @Override
    @Transactional(readOnly =  true)
    public List<E> searchByExample(String coreId, D example, PagingDto pagingDto) throws BaseVaselineServerException {
        return BaseMultiDaoReadOnlyApiImplHelper.searchByExample(this, coreId, example, pagingDto);
    }

    @Override
    public abstract DAO getDaoFor(String coreId) throws BaseVaselineServerException;

    @Autowired
    public void setDaoList(List<DAO> daoList) {
        this.daoList = daoList;
    }
}
