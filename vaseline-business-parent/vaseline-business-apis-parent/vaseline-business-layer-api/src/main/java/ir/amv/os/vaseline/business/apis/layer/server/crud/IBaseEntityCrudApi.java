package ir.amv.os.vaseline.business.apis.layer.server.crud;

import ir.amv.os.vaseline.business.apis.layer.server.ro.IBaseEntityReadOnlyApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;

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
