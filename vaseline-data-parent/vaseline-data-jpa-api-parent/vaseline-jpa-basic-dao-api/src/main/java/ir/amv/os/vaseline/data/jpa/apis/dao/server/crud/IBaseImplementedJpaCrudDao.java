package ir.amv.os.vaseline.data.jpa.apis.dao.server.crud;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.IBaseImplementedJpaReadOnlyDao;

import javax.persistence.EntityManager;
import java.io.Serializable;

public interface IBaseImplementedJpaCrudDao<E extends IBaseEntity<Id>, Id extends Serializable>
        extends IBaseImplementedJpaReadOnlyDao<E, Id>, IBaseCrudDao<E, Id> {

    @Override
    default Id save(E entity) {
        EntityManager em = getEntityManager();
        em.persist(entity);
        em.flush();
        return entity.getId();
    }

    @Override
    default void update(E entity) {
        EntityManager em = getEntityManager();
        em.merge(entity);
        em.flush();
    }

    @Override
    default void delete(E entity) {
        EntityManager em = getEntityManager();
        em.remove(entity);
        em.flush();
    }

}
