package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.crud;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaReadOnlyDao;

import javax.persistence.EntityManager;
import java.io.Serializable;

public interface IDefaultJpaCrudDao<I extends Serializable, E extends IBaseEntity<I>>
        extends IDefaultJpaReadOnlyDao<I, E>, IBaseCrudDao<I, E> {

    @Override
    default I save(E entity) {
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
