package ir.amv.os.vaseline.data.spring.hibernate.server.crud;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.crud.IDefaultHibernateCrudDao;
import ir.amv.os.vaseline.data.spring.hibernate.server.ro.BaseSpringHibernateReadOnlyDaoImpl;

import java.io.Serializable;

/**
 * @author Amir
 */
public class BaseSpringHibernateCrudDaoImpl<E extends IBaseEntity<Id>, Id extends Serializable>
        extends BaseSpringHibernateReadOnlyDaoImpl<E, Id>
        implements IDefaultHibernateCrudDao<E, Id> {


}
