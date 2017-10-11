package ir.amv.os.vaseline.data.hibernate.apis.dao.server.crud;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.data.apis.dao.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.BaseReadOnlyHibernateDaoImpl;
import org.hibernate.Session;

import java.io.Serializable;

/**
 * Created by AMV on 2/8/2016.
 */
public class BaseCrudHibernateDaoImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends BaseReadOnlyHibernateDaoImpl<E, Id>
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
