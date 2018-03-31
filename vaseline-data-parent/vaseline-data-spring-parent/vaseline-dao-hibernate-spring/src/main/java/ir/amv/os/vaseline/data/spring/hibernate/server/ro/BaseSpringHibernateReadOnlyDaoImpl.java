package ir.amv.os.vaseline.data.spring.hibernate.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.IBaseImplementedHibernateReadOnlyDao;
import ir.amv.os.vaseline.data.spring.hibernate.server.base.BaseSpringHibernateDaoImpl;

import java.io.Serializable;

/**
 * @author Amir
 */
public class BaseSpringHibernateReadOnlyDaoImpl<E extends IBaseEntity<Id>, Id extends Serializable>
        extends BaseSpringHibernateDaoImpl
        implements IBaseImplementedHibernateReadOnlyDao<E, Id> {
}
