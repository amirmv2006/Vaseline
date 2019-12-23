package ir.amv.os.vaseline.data.spring.hibernate.server.ro;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.IDefaultHibernateReadOnlyDao;
import ir.amv.os.vaseline.data.spring.hibernate.server.base.BaseSpringHibernateDaoImpl;

import java.io.Serializable;

/**
 * @author Amir
 */
public class BaseSpringHibernateReadOnlyDaoImpl<I extends Serializable, E extends IBaseBusinessModel<I>>
        extends BaseSpringHibernateDaoImpl
        implements IDefaultHibernateReadOnlyDao<I, E> {
}
