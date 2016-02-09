package ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.crud.dao;

import ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.ro.dao.BaseReadOnlyHibernateDaoImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.IBaseCrudDao;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import org.hibernate.Session;

import java.io.Serializable;

/**
 * Created by AMV on 2/8/2016.
 */
public class BaseCrudHibernateDaoImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends BaseReadOnlyHibernateDaoImpl<E, D, Id>
        implements IBaseCrudDao<E, D, Id> {

    @Override
    public Id save(E entity) {
        Session currentSession = getCurrentSession();
        Serializable id = currentSession.save(entity);
        currentSession.flush();
        return (Id) id;
    }

    @Override
    public void update(E entity) {
        Session currentSession = getCurrentSession();
        currentSession.update(entity);
        currentSession.flush();
    }

    @Override
    public void delete(E entity) {
        Session currentSession = getCurrentSession();
        currentSession.delete(entity);
        currentSession.flush();
    }

}
