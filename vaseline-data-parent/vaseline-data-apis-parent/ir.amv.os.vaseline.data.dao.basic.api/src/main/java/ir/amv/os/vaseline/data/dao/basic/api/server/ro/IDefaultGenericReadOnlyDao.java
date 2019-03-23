package ir.amv.os.vaseline.data.dao.basic.api.server.ro;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.io.Serializable;

/**
 * TODO move to another module
 * @author Amir
 */
public interface IDefaultGenericReadOnlyDao<E extends IBaseEntity<Id>, Id extends Serializable>
        extends IBaseReadOnlyDao<E, Id> {

    @Override
    default E newEntity() throws IllegalAccessException, InstantiationException {
        return getEntityClass().newInstance();
    }

    @Override
    default Class<? extends E> getEntityClass() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClassesDeprecated(getClass(), IBaseReadOnlyDao.class);
        if (genericArgumentClasses != null) {
            for (Class<?> genericArgumentClass : genericArgumentClasses) {
                if (IBaseEntity.class.isAssignableFrom(genericArgumentClass)) {
                    return (Class<E>) genericArgumentClass;
                }
            }
        }
        return null;
    }

    @Override
    default void setEntityClass(Class<E> entityClass) {
        // no need for this, I'm finding the generics on my own :)
    }

}
