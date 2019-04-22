package ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.crud;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.IDefaultHibernateReadOnlyDao;
import org.hibernate.Session;

import java.io.Serializable;

public interface IDefaultHibernateCrudDao<E extends IBaseEntity<Id>, Id extends Serializable>
        extends IDefaultHibernateReadOnlyDao<E, Id>, IBaseCrudDao<E, Id> {

    @Override
    default Id save(E entity) {
        Session currentSession = getCurrentSession();
        Id id = (Id) currentSession.save(entity);
        currentSession.flush();
        return id;
    }

    @Override
    default void update(E entity) {
        Session currentSession = getCurrentSession();
        currentSession.update(entity);
        currentSession.flush();
    }

    @Override
    default void delete(E entity) {
        Session currentSession = getCurrentSession();
        currentSession.delete(entity);
        currentSession.flush();
    }
}
