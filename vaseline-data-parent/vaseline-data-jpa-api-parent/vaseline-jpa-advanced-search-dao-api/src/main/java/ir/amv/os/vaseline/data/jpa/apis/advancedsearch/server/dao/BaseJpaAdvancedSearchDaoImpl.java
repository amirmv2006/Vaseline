package ir.amv.os.vaseline.data.jpa.apis.advancedsearch.server.dao;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.jpa.apis.simplesearch.server.dao.BaseJpaSimpleSearchDaoImpl;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.io.Serializable;

public class BaseJpaAdvancedSearchDaoImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, SO extends IBaseSearchObject, Id extends Serializable>
        extends BaseJpaSimpleSearchDaoImpl<E, D ,Id>
        implements IBaseImplementedJpaAdvancedSearchDao<E, SO, Id> {

    private Class<SO> searchObjectClass;

    public BaseJpaAdvancedSearchDaoImpl() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass(), IBaseImplementedJpaAdvancedSearchDao.class);
        if (genericArgumentClasses != null) {
            setEntityClass((Class<E>) genericArgumentClasses[0]);
            searchObjectClass = (Class<SO>) genericArgumentClasses[2];
        }
    }

}