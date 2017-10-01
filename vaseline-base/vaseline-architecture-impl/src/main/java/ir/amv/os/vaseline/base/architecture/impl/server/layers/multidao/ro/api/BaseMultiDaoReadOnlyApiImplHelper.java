package ir.amv.os.vaseline.base.architecture.impl.server.layers.multidao.ro.api;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.api.BaseReadOnlyApiImplHelper;
import ir.amv.os.vaseline.data.apis.dao.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.base.architecture.server.layers.multidao.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public class BaseMultiDaoReadOnlyApiImplHelper {

    private BaseMultiDaoReadOnlyApiImplHelper() {
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> E getById(
            IBaseMultiDaoReadOnlyApi<E, D, Id> api,
            String coreId,
            Id id)
            throws BaseVaselineServerException {
        IBaseReadOnlyDao<E, Id> dao = api.getDaoFor(coreId);
        return BaseReadOnlyApiImplHelper.getById(api, dao, id);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> Long countAll(
            IBaseMultiDaoReadOnlyApi<E, D, Id> api,
            String coreId)
            throws BaseVaselineServerException {
        IBaseReadOnlyDao<E, Id> dao = api.getDaoFor(coreId);
        return BaseReadOnlyApiImplHelper.countAll(api, dao);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> List<E> getAll(
            IBaseMultiDaoReadOnlyApi<E, D, Id> api,
            String coreId)
            throws BaseVaselineServerException {
        IBaseReadOnlyDao<E, Id> dao = api.getDaoFor(coreId);
        return BaseReadOnlyApiImplHelper.getAll(api, dao);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> List<E> getAll(
            IBaseMultiDaoReadOnlyApi<E, D, Id> api,
            String coreId,
            PagingDto pagingDto)
            throws BaseVaselineServerException {
        IBaseReadOnlyDao<E, Id> dao = api.getDaoFor(coreId);
        return BaseReadOnlyApiImplHelper.getAll(api, dao, pagingDto);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> Long countByExample(
            IBaseMultiDaoReadOnlyApi<E, D, Id> api,
            String coreId,
            D example)
            throws BaseVaselineServerException {
        IBaseReadOnlyDao<E, Id> dao = api.getDaoFor(coreId);
        return BaseReadOnlyApiImplHelper.countByExample(api, dao, example);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> List<E> searchByExample(
            IBaseMultiDaoReadOnlyApi<E, D, Id> api,
            String coreId,
            D example)
            throws BaseVaselineServerException {
        IBaseReadOnlyDao<E, Id> dao = api.getDaoFor(coreId);
        return BaseReadOnlyApiImplHelper.searchByExample(api, dao, example);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> List<E> searchByExample(
            IBaseMultiDaoReadOnlyApi<E, D, Id> api,
            String coreId,
            D example,
            PagingDto pagingDto)
            throws BaseVaselineServerException {
        IBaseReadOnlyDao<E, Id> dao = api.getDaoFor(coreId);
        return BaseReadOnlyApiImplHelper.searchByExample(api, dao, example, pagingDto);
    }

}
