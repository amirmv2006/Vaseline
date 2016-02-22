package ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.ro.api;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.api.BaseApiImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.ent.ro.api.IBaseEntityReadOnlyApi;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.util.reflection.ReflectionUtil;

import java.io.Serializable;

/**
 * Created by AMV on 2/9/2016.
 */
public class BaseEntityReadOnlyApiImpl<E extends IBaseEntity<Id>, Id extends Serializable>
        extends BaseApiImpl
        implements IBaseEntityReadOnlyApi<E>{

    private Class<E> entityClass;

    public BaseEntityReadOnlyApiImpl() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass());
        if (genericArgumentClasses != null) {
            entityClass = (Class<E>) genericArgumentClasses[0];
        }
    }

    @Override
    public void postGet(E entity) throws BaseVaselineServerException {
        BaseEntityReadOnlyApiImplHelper.postGet(entity);
    }

    @Override
    public Class<E> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<E> entityClass) {
        this.entityClass = entityClass;
    }
}
