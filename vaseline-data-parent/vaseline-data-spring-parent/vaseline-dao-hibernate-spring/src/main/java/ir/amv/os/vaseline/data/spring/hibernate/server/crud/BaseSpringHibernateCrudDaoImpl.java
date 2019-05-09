package ir.amv.os.vaseline.data.spring.hibernate.server.crud;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.crud.IDefaultHibernateCrudDao;
import ir.amv.os.vaseline.data.spring.hibernate.server.ro.BaseSpringHibernateReadOnlyDaoImpl;

import java.io.Serializable;

/**
 * @author Amir
 */
public class BaseSpringHibernateCrudDaoImpl<I extends Serializable, E extends IBaseEntity<I>>
        extends BaseSpringHibernateReadOnlyDaoImpl<I, E>
        implements IDefaultHibernateCrudDao<I, E> {


}
