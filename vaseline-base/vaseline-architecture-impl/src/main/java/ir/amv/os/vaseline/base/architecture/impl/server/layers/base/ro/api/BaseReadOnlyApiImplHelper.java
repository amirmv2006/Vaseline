package ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.api;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.IBaseReadOnlyDao;
import ir.amv.os.vaseline.base.architecture.server.layers.ent.ro.api.IBaseEntityReadOnlyApi;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/8/2016.
 */
public class BaseReadOnlyApiImplHelper {

    private BaseReadOnlyApiImplHelper() {
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            E getById(
            IBaseEntityReadOnlyApi<E> api,
            IBaseReadOnlyDao<E, D, Id> dao,
            Id id)
            throws BaseVaselineServerException {
        E findById = dao.getById(id);
        api.postGet(findById);
        return findById;
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            Long countAll(
            IBaseEntityReadOnlyApi<E> api,
            IBaseReadOnlyDao<E, D, Id> dao)
            throws BaseVaselineServerException {
        Long countAll = dao.countAll();
        return countAll;
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            List<E> getAll(
            IBaseEntityReadOnlyApi<E> api,
            IBaseReadOnlyDao<E, D, Id> dao)
            throws BaseVaselineServerException {
        List<E> all = dao.getAll();
        if (all != null) {
            for (E entity : all) {
                api.postGet(entity);
            }
        }
        return all;
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            List<E> getAll(
            IBaseEntityReadOnlyApi<E> api,
            IBaseReadOnlyDao<E, D, Id> dao,
            PagingDto pagingDto)
            throws BaseVaselineServerException {
        List<E> all = dao.getAll(pagingDto);
        if (all != null) {
            for (E entity : all) {
                api.postGet(entity);
            }
        }
        return all;
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            Long countByExample(
            IBaseEntityReadOnlyApi<E> api,
            IBaseReadOnlyDao<E, D, Id> dao,
            D example)
            throws BaseVaselineServerException {
        Long countByExample = dao.countByExample(example);
        return countByExample;
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            List<E> searchByExample(
            IBaseEntityReadOnlyApi<E> api,
            IBaseReadOnlyDao<E, D, Id> dao,
            D example)
            throws BaseVaselineServerException {
        List<E> searchByExample = dao.searchByExample(example);
        if (searchByExample != null) {
            for (E entity : searchByExample) {
                api.postGet(entity);
            }
        }
        return searchByExample;
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            List<E> searchByExample(
            IBaseEntityReadOnlyApi<E> api,
            IBaseReadOnlyDao<E, D, Id> dao,
            D example,
            PagingDto pagingDto)
            throws BaseVaselineServerException {
        List<E> searchByExample = dao.searchByExample(example, pagingDto);
        if (searchByExample != null) {
            for (E entity : searchByExample) {
                api.postGet(entity);
            }
        }
        return searchByExample;
    }

}
