package ir.amv.os.vaseline.business.basic.api.server.ro;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.api.server.base.IBaseApi;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseEntityReadOnlyApi<E extends IBaseEntity<?>> extends IBaseApi {

    void postGet(E entity) throws BaseVaselineServerException;

    Class<? extends E> getEntityClass();

    E newEntity() throws BaseVaselineServerException;
}
