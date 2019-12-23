package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.crud;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.data.dao.basic.api.crud.IBaseCrudRepository;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaReadOnlyRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public interface IDefaultJpaCrudRepository<I extends Serializable, E extends IBaseBusinessModel<I>>
        extends IDefaultJpaReadOnlyRepository<I, E>, IBaseCrudRepository<I, E> {

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
