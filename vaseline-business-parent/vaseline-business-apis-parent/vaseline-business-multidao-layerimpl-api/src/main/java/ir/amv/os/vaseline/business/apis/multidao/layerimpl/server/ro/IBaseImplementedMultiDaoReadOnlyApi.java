package ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.notsupported.VaselineFeatureNotSupportedException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.business.apis.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.business.apis.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.business.apis.multidao.layer.server.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.server.ro.IBaseReadOnlyDao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseImplementedMultiDaoReadOnlyApi<E extends IBaseEntity<Id>, Id extends Serializable, Category>
        extends IBaseMultiDaoReadOnlyApi<E, Id, Category>, IBaseImplementedReadOnlyApi<E, Id> {

    @Override
    default E getById(Id id) throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.getById(id);
    }
    default E getById(Category category, Id id) throws BaseVaselineServerException {
        E byId = getReadDaoFor(category).getById(id);
        postGet(byId);
        return byId;
    }

    @Override
    default Long countAll() throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.countAll();
    }
    default Long countAll(Category category) throws BaseVaselineServerException {
        return getReadDaoFor(category).countAll();
    }

    @Override
    default List<E> getAll() throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.getAll();
    }
    default List<E> getAll(Category category) throws BaseVaselineServerException {
        List<E> all = getReadDaoFor(category).getAll();
        postGetList(all);
        return all;
    }

    @Override
    default List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException {
        return IBaseMultiDaoReadOnlyApi.super.getAll(pagingDto);
    }
    @Override
    default List<E> getAll(Category category, PagingDto pagingDto) throws BaseVaselineServerException {
        List<E> all = getReadDaoFor(category).getAll(pagingDto);
        postGetList(all);
        return all;
    }

    @Override
    default IBaseReadOnlyDao<E, Id> getReadDao() {
        throw new VaselineFeatureNotSupportedException();
    }
    IBaseReadOnlyDao<E, Id> getReadDaoFor(Category category) throws BaseVaselineServerException;
}
