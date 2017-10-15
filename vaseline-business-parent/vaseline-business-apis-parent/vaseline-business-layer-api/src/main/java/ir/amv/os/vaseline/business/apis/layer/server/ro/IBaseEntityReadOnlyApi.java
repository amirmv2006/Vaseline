package ir.amv.os.vaseline.business.apis.layer.server.ro;

import ir.amv.os.vaseline.business.apis.layer.server.base.IBaseApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseEntityReadOnlyApi<E extends IBaseEntity<?>> extends IBaseApi {

    void postGet(E entity) throws BaseVaselineServerException;

    Class<E> getEntityClass();
}
