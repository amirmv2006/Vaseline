package ir.amv.os.vaseline.service.basic.def.server.crud;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.validation.IEntityDeleteValidation;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.validation.IEntitySaveValidation;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.validation.IEntityUpdateValidation;
import ir.amv.os.vaseline.business.basic.api.layer.crud.IBaseCrudApi;
import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.api.layer.crud.IBaseCrudService;
import ir.amv.os.vaseline.service.basic.def.server.ro.IDefaultReadOnlyService;

import java.io.Serializable;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IDefaultCrudService<
            I extends Serializable,
            D extends IBaseDto<I>,
            M extends IBaseBusinessModel<I>,
            A extends IBaseCrudApi<I, M>
        >
        extends IBaseCrudService<I, D>, IDefaultReadOnlyService<I, D, M, A> {

    @Override
    default I save(D t) throws BaseExternalException {
        return handleExceptions(() -> {
            M ent = convertTo(t, validationGroupsForSave());
            return getApi().save(ent);
        });
    }

    @Override
    default void update(D t) throws BaseExternalException {
        handleExceptions(() -> {
            M entity = convertTo(t, validationGroupsForUpdate());
            getApi().update(entity);
            return null;
        });
    }

    @Override
    default void delete(D id) throws BaseExternalException {
        handleExceptions(() -> {
            M entity = convertTo(id, validationGroupsForDelete());
            getApi().delete(entity);
            return null;
        });
    }

    @Override
    default void deleteById(I id) throws BaseExternalException {
        handleExceptions(() -> {
            getApi().delete(id);
            return null;
        });
    }

    // crud validation
    default Class<?>[] validationGroupsForSave() {
        return new Class<?>[]{IEntitySaveValidation.class};
    }

    default Class<?>[] validationGroupsForUpdate() {
        return new Class<?>[]{IEntityUpdateValidation.class};
    }

    default Class<?>[] validationGroupsForDelete() {
        return new Class<?>[]{IEntityDeleteValidation.class};
    }
}
