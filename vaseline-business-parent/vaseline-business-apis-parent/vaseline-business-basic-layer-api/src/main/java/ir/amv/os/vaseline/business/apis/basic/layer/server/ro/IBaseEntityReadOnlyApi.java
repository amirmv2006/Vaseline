package ir.amv.os.vaseline.business.apis.basic.layer.server.ro;

import ir.amv.os.vaseline.business.apis.basic.layer.server.base.IBaseApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseEntityReadOnlyApi<E extends IBaseEntity<?>> extends IBaseApi {

    void postGet(E entity) throws BaseVaselineServerException;

    Class<E> getEntityClass();

    E newEntity() throws BaseVaselineServerException;
}
