package ir.amv.os.vaseline.data.hibernate.apis.simplesearch.server.dao;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.BaseReadOnlyHibernateDaoImpl;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.io.Serializable;

public class BaseSimpleSearchHibernateDaoImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends BaseReadOnlyHibernateDaoImpl<E, Id>
        implements IBaseHibernateSimpleSearchDao<E, D, Id> {

    private Class<D> dtoClass;

    public BaseSimpleSearchHibernateDaoImpl() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass(), IBaseHibernateSimpleSearchDao.class);
        if (genericArgumentClasses != null) {
            dtoClass = (Class<D>) genericArgumentClasses[1];
        }
    }

    @Override
    public Class<D> getDtoClass() {
        return dtoClass;
    }
}
