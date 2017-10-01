package ir.amv.os.vaseline.data.hibernate.apis.advancedsearch.server.dao;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.hibernate.apis.simplesearch.server.dao.BaseSimpleSearchHibernateDaoImpl;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.io.Serializable;

public class BaseAdvancedSearchHibernateDaoImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, SO extends IBaseSearchObject, Id extends Serializable>
        extends BaseSimpleSearchHibernateDaoImpl<E, D ,Id>
        implements IBaseHibernateAdvancedSearchDao<E, SO, Id> {

    private Class<SO> searchObjectClass;

    public BaseAdvancedSearchHibernateDaoImpl() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass(), IBaseHibernateAdvancedSearchDao.class);
        if (genericArgumentClasses != null) {
            setEntityClass((Class<E>) genericArgumentClasses[0]);
            searchObjectClass = (Class<SO>) genericArgumentClasses[1];
        }
    }

    @Override
    public Class<SO> getSearchObjectClass() {
        return searchObjectClass;
    }

}