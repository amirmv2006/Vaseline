package ir.amv.os.vaseline.service.apis.basic.layerimpl.server.crud;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.basics.apis.core.shared.validation.IEntityDeleteValidation;
import ir.amv.os.vaseline.basics.apis.core.shared.validation.IEntitySaveValidation;
import ir.amv.os.vaseline.basics.apis.core.shared.validation.IEntityUpdateValidation;
import ir.amv.os.vaseline.business.apis.basic.layer.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.service.apis.basic.layer.server.crud.IBaseCrudService;
import ir.amv.os.vaseline.service.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyService;

import java.io.Serializable;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseImplementedCrudService<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends
        Serializable, Api extends IBaseCrudApi<E, Id>>
        extends IBaseCrudService<D, Id>, IBaseImplementedReadOnlyService<E, D, Id, Api> {

    @Override
    default Id save(D t) throws BaseVaselineClientException {
        try {
            E ent = convertDtoToEntity(t, validationGroupsForSave());
            return getApi().save(ent);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default void update(D t) throws BaseVaselineClientException {
        try {
            E entity = convertDtoToEntity(t, validationGroupsForUpdate());
            getApi().update(entity);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default void delete(D id) throws BaseVaselineClientException {
        try {
            E entity = convertDtoToEntity(id, validationGroupsForDelete());
            getApi().delete(entity);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default void deleteById(Id id) throws BaseVaselineClientException {
        try {
            getApi().delete(id);
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
