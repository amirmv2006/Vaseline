package ir.amv.os.vaseline.service.basic.def.server.crud;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.validation.IEntityDeleteValidation;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.validation.IEntitySaveValidation;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.validation.IEntityUpdateValidation;
import ir.amv.os.vaseline.business.basic.api.layer.crud.IBaseCrudApi;
import ir.amv.os.vaseline.service.basic.api.server.crud.IBaseCrudService;
import ir.amv.os.vaseline.service.basic.def.server.ro.IDefaultReadOnlyService;

import java.io.Serializable;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IDefaultCrudService<Id extends
        Serializable, E extends IBaseBusinessModel<Id>, D extends IBaseDto<Id>, Api extends IBaseCrudApi<Id, E>>
        extends IBaseCrudService<Id, D>, IDefaultReadOnlyService<Id, E, D, Api> {

    @Override
    default Id save(D t) throws BaseExternalException {
        try {
            E ent = convertDtoToEntity(t, validationGroupsForSave());
            return getApiProxy().save(ent);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default void update(D t) throws BaseExternalException {
        try {
            E entity = convertDtoToEntity(t, validationGroupsForUpdate());
            getApiProxy().update(entity);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default void delete(D id) throws BaseExternalException {
        try {
            E entity = convertDtoToEntity(id, validationGroupsForDelete());
            getApiProxy().delete(entity);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default void deleteById(Id id) throws BaseExternalException {
        try {
            getApiProxy().delete(id);
        } catch (Exception e) {
            throw convertException(e);
        }
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
