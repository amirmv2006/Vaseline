package ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.api;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.ro.api.BaseEntityReadOnlyApiImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.api.IBaseReadOnlyApi;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.IBaseReadOnlyDao;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/8/2016.
 */
public class BaseReadOnlyApiImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, DAO extends IBaseReadOnlyDao<E, D, Id>>
        extends BaseEntityReadOnlyApiImpl<E, Id>
        implements IBaseReadOnlyApi<E, D, Id> {

    protected DAO dao;

    @Override
    @Transactional(readOnly = true)
    public E getById(Id id) throws BaseVaselineServerException {
        return BaseReadOnlyApiImplHelper.getById(this, getDao(), id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countAll() throws BaseVaselineServerException {
        return BaseReadOnlyApiImplHelper.countAll(this, getDao());
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> getAll() throws BaseVaselineServerException {
        return BaseReadOnlyApiImplHelper.getAll(this, getDao());
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException {
        return BaseReadOnlyApiImplHelper.getAll(this, getDao(), pagingDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByExample(D example) throws BaseVaselineServerException {
        return BaseReadOnlyApiImplHelper.countByExample(this, getDao(), example);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> searchByExample(D example) throws BaseVaselineServerException {
        return BaseReadOnlyApiImplHelper.searchByExample(this, getDao(), example);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineServerException {
        return BaseReadOnlyApiImplHelper.searchByExample(this, getDao(), example, pagingDto);
    }

    @Autowired
    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public DAO getDao() {
        return dao;
    }
}
