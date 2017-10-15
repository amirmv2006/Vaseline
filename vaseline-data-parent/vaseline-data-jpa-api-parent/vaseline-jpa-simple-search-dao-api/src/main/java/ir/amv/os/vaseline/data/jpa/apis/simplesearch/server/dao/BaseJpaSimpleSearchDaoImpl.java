package ir.amv.os.vaseline.data.jpa.apis.simplesearch.server.dao;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.BaseReadOnlyJpaDaoImpl;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.io.Serializable;

public class BaseJpaSimpleSearchDaoImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends BaseReadOnlyJpaDaoImpl<E, Id>
        implements IBaseImplementedJpaSimpleSearchDao<E, D, Id> {

    private Class<D> dtoClass;

    public BaseJpaSimpleSearchDaoImpl() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass(), IBaseImplementedJpaSimpleSearchDao.class);
        if (genericArgumentClasses != null) {
            dtoClass = (Class<D>) genericArgumentClasses[1];
        }
    }

}
