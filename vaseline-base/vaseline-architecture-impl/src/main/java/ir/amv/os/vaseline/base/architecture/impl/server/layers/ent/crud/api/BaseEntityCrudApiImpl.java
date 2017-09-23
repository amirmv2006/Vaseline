package ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.crud.api;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.ro.api.BaseEntityReadOnlyApiImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.ent.crud.api.IBaseEntityCrudApi;
import ir.amv.os.vaseline.base.core.api.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.api.server.base.exc.BaseVaselineServerException;

import java.io.Serializable;

/**
 * Created by AMV on 2/9/2016.
 */
public class BaseEntityCrudApiImpl<E extends IBaseEntity<Id>, Id extends Serializable>
        extends BaseEntityReadOnlyApiImpl<E, Id>
        implements IBaseEntityCrudApi<E> {

    @Override
    public void preSave(E entity) throws BaseVaselineServerException {
        BaseEntityCrudApiImplHelper.preSave(entity);
    }

    @Override
    public void postSave(E entity) throws BaseVaselineServerException {
        BaseEntityCrudApiImplHelper.postSave(entity);
    }

    @Override
    public void preUpdate(E entity) throws BaseVaselineServerException {
        BaseEntityCrudApiImplHelper.preUpdate(entity);
    }

    @Override
    public void postUpdate(E entity) throws BaseVaselineServerException {
        BaseEntityCrudApiImplHelper.postUpdate(entity);
    }

    @Override
    public void preDelete(E entity) throws BaseVaselineServerException {
        BaseEntityCrudApiImplHelper.preDelete(entity);
    }

    @Override
    public void postDelete(E entity) throws BaseVaselineServerException {
        BaseEntityCrudApiImplHelper.postDelete(entity);
    }
}
