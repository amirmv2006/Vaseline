package ir.amv.os.vaseline.base.architecture.server.layers.ent.crud.api;

import ir.amv.os.vaseline.base.architecture.server.layers.ent.ro.api.IBaseEntityReadOnlyApi;
import ir.amv.os.vaseline.base.core.api.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.api.server.base.exc.BaseVaselineServerException;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseEntityCrudApi<E extends IBaseEntity<?>>
        extends IBaseEntityReadOnlyApi<E> {

    void preSave(E entity) throws BaseVaselineServerException;
    void postSave(E entity) throws BaseVaselineServerException;

    void preUpdate(E entity) throws BaseVaselineServerException;
    void postUpdate(E entity) throws BaseVaselineServerException;

    void preDelete(E entity) throws BaseVaselineServerException;
    void postDelete(E entity) throws BaseVaselineServerException;
}
