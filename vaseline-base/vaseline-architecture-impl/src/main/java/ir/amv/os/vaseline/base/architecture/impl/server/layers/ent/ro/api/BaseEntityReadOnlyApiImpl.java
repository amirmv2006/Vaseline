package ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.ro.api;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.api.BaseApiImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.ent.ro.api.IBaseEntityReadOnlyApi;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;

import java.io.Serializable;

/**
 * Created by AMV on 2/9/2016.
 */
public class BaseEntityReadOnlyApiImpl<E extends IBaseEntity<Id>, Id extends Serializable>
        extends BaseApiImpl
        implements IBaseEntityReadOnlyApi<E>{

    @Override
    public void postGet(E entity) throws BaseVaselineServerException {
        BaseEntityReadOnlyApiImplHelper.postGet(entity);
    }
}
