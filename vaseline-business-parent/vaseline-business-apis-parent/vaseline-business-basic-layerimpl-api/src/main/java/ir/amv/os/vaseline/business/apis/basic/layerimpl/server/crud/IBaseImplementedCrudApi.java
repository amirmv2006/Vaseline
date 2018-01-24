package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.crud;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineDbOpMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionOneImpl;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseImplementedCrudApi<E extends IBaseEntity<Id>, Id extends Serializable, Dao extends
        IBaseCrudDao<E, Id>>
        extends IBaseCrudApi<E, Id>, IBaseImplementedEntityCrudApi<E>, IBaseImplementedReadOnlyApi<E, Id, Dao> {

    @Transactional
    default Id save(E entity) throws BaseVaselineServerException {
        Method saveMethod = getDeclaredMethod(IBaseImplementedCrudApi.class, "save", IBaseEntity.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                        getClass(), saveMethod, entity, e -> {
                    preSave(e);
                    Id id = getDao().save(e);
                    postSave(e);
                    return id;
                }, VaselineDbOpMetadata.WRITE)
        );
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    default List<Id> saveBatch(List<E> entities) throws BaseVaselineServerException {
        Method saveBatchMethod = getDeclaredMethod(IBaseImplementedCrudApi.class, "saveBatch", List.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                        getClass(), saveBatchMethod, entities, es -> {
                    List<Id> result = new ArrayList<>();
                    if (es != null) {
                        for (E entity : es) {
                            Id id = save(entity);
                            result.add(id);
                        }
                    }
                    return result;
                }, VaselineDbOpMetadata.WRITE)
        );
    }

    @Transactional
    default void update(E entity) throws BaseVaselineServerException {
        Method updateMethod = getDeclaredMethod(IBaseImplementedCrudApi.class, "update", IBaseEntity.class);
        doBusinessAction(new BusinessFunctionOneImpl<E, Void>(
                        getClass(), updateMethod, entity, e -> {
                    preUpdate(e);
                    getDao().update(e);
                    postUpdate(e);
                    return null;
                }, VaselineDbOpMetadata.WRITE)
        );
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    default void updateBatch(List<E> entities) throws BaseVaselineServerException {
        Method updateBatchMethod = getDeclaredMethod(IBaseImplementedCrudApi.class, "updateBatch", List.class);
        doBusinessAction(new BusinessFunctionOneImpl<List<E>, Void>(
                        getClass(), updateBatchMethod, entities, es -> {
                    if (es != null) {
                        for (E entity : es) {
                            update(entity);
                        }
                    }
                    return null;
                }, VaselineDbOpMetadata.WRITE)
        );
    }

    @Transactional
    default void delete(E entity) throws BaseVaselineServerException {
        Method deleteEntMethod = getDeclaredMethod(IBaseImplementedCrudApi.class, "delete", IBaseEntity.class);
        doBusinessAction(new BusinessFunctionOneImpl<E, Void>(
                        getClass(), deleteEntMethod, entity, e -> {
                    preDelete(e);
                    getDao().delete(e);
                    postDelete(e);
                    return null;
                }, VaselineDbOpMetadata.WRITE)
        );
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    default void deleteBatch(List<E> entities) throws BaseVaselineServerException {
        Method deleteBatchMethod = getDeclaredMethod(IBaseImplementedCrudApi.class, "deleteBatch", List.class);
        doBusinessAction(new BusinessFunctionOneImpl<List<E>, Void>(
                        getClass(), deleteBatchMethod, entities, es -> {
                    if (es != null) {
                        for (E entity : es) {
                            delete(entity);
                        }
                    }
                    return null;
                }, VaselineDbOpMetadata.WRITE)
        );
    }

    @Transactional
    default void delete(Id id) throws BaseVaselineServerException {
        Method deleteIdMethod = getDeclaredMethod(IBaseImplementedCrudApi.class, "delete", Serializable.class);
        doBusinessAction(new BusinessFunctionOneImpl<Id, Void>(
                        getClass(), deleteIdMethod, id, e -> {
                    E byId = getById(e);
                    delete(byId);
                    return null;
                }, VaselineDbOpMetadata.WRITE)
        );
    }

}
