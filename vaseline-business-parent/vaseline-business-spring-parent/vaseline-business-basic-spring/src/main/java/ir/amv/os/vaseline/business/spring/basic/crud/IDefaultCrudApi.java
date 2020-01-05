package ir.amv.os.vaseline.business.spring.basic.crud;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessEntity;
import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.crud.IBaseCrudApi;
import ir.amv.os.vaseline.business.spring.basic.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.data.basic.api.base.IBaseCrudRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IDefaultCrudApi<
            I extends Serializable,
            M extends IBaseBusinessEntity<I, M>,
            E extends IBasePersistenceModel<I>,
            R extends IBaseCrudRepository<I, E>
        >
        extends IBaseCrudApi<I, M>, IDefaultModelCrudApi<M>, IDefaultReadOnlyApi<I, M, E, R> {

    @Override
    @Transactional
    default I save(M model) throws BaseBusinessException {
        preSave(model);
        E saved = getRepository().save(convertTo(model));
        postSave(convertFrom(saved));
        return saved.getId();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    default List<I> saveBatch(List<M> models) throws BaseBusinessException {
        for (M model : models) {
            preSave(model);
        }
        Iterable<E> saved = getRepository().saveAll(models.stream()
                .map(this::convertTo)
                .collect(Collectors.toList())
        );
        List<I> result = new ArrayList<>();
        for (E pm : saved) {
            result.add(pm.getId());
            postSave(convertFrom(pm));
        }
        return result;
    }

    @Override
    @Transactional
    default void update(M model) throws BaseBusinessException {
        preUpdate(model);
        E updated = getRepository().save(convertTo(model));
        postUpdate(convertFrom(updated));
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    default void updateBatch(List<M> models) throws BaseBusinessException {
        for (M model : models) {
            preUpdate(model);
        }
        Iterable<E> saved = getRepository().saveAll(models.stream()
                .map(this::convertTo)
                .collect(Collectors.toList())
        );
        for (E pm : saved) {
            postUpdate(convertFrom(pm));
        }
    }

    @Override
    @Transactional
    default void delete(M model) throws BaseBusinessException {
        preDelete(model);
        getRepository().delete(convertTo(model));
        postDelete(model);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    default void deleteBatch(List<M> models) throws BaseBusinessException {
        for (M model : models) {
            preDelete(model);
        }
        getRepository().deleteAll(models.stream()
                .map(this::convertTo)
                .collect(Collectors.toList())
        );
        for (M model : models) {
            postDelete(model);
        }
    }

    @Override
    @Transactional
    default void delete(I id) throws BaseBusinessException {
        M byId = getRepository().findById(id)
                .map(this::convertFrom)
                .orElse(null);
        delete(byId);
    }

}
