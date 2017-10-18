package ir.amv.os.vaseline.data.hibernate.apis.dao.server.crud;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.IBaseImplementedHibernateReadOnlyDao;
import org.hibernate.Session;

import java.io.Serializable;

public interface IBaseImplementedHibernateCrudDao<E extends IBaseEntity<Id>, Id extends Serializable>
        extends IBaseImplementedHibernateReadOnlyDao<E, Id>, IBaseCrudDao<E, Id> {

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
