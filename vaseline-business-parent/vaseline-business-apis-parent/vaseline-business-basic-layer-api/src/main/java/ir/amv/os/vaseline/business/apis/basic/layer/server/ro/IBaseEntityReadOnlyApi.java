package ir.amv.os.vaseline.business.apis.basic.layer.server.ro;

import ir.amv.os.vaseline.business.apis.basic.layer.server.base.IBaseApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseEntityReadOnlyApi<E extends IBaseEntity<?>> extends IBaseApi {

    void postGet(E entity) throws BaseVaselineServerException;

    default Class<E> getEntityClass() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass());
        if (genericArgumentClasses != null) {
            for (Class<?> genericArgumentClass : genericArgumentClasses) {
                if (IBaseEntity.class.isAssignableFrom(genericArgumentClass)) {
                    return (Class<E>) genericArgumentClass;
                }
            }
        }
        return null;
    }
}
