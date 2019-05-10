package ir.amv.os.vaseline.security.authorization.rbac.business.osgi;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.proxy.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.business.basic.def.server.crud.IDefaultCrudApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.security.authorization.business.def.IDefaultNotSecuredCrudApi;

/**
 * @author Amir
 */
public abstract class BaseApiImpl<E extends IBaseEntity<Long>, Dao extends IBaseCrudDao<Long, E>>
        extends ProxyAwareImpl
        implements
        IDefaultCrudApi<Long, E, Dao>,
        IDefaultNotSecuredCrudApi<E, Long, Dao> {

}
