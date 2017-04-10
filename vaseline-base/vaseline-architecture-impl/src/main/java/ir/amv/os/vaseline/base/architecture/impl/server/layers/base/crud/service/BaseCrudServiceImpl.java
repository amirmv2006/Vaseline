package ir.amv.os.vaseline.base.architecture.impl.server.layers.base.crud.service;

import ir.amv.os.vaseline.base.core.shared.validation.IEntityDeleteValidation;
import ir.amv.os.vaseline.base.core.shared.validation.IEntitySaveValidation;
import ir.amv.os.vaseline.base.core.shared.validation.IEntityUpdateValidation;
import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.service.BaseReadOnlyServiceImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.api.IBaseCrudApi;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.service.IBaseCrudService;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.exc.BaseVaselineClientException;

import java.io.Serializable;

/**
 * Created by AMV on 2/8/2016.
 */
public class BaseCrudServiceImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, API extends IBaseCrudApi<E, D, Id>>
        extends BaseReadOnlyServiceImpl<E, D, Id, API>
        implements IBaseCrudService<D, Id> {

    @Override
    public Id save(D t) throws BaseVaselineClientException {
        try {
            E ent = convertDtoToEntity(t, validationGroupsForSave());
            Id id = api.save(ent);
            return id;
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public void update(D t) throws BaseVaselineClientException {
        try {
            E entity = convertDtoToEntity(t, validationGroupsForUpdate());
            api.update(entity);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public void delete(D id) throws BaseVaselineClientException {
        try {
            E entity = convertDtoToEntity(id, validationGroupsForDelete());
            api.delete(entity);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public void deleteById(Id id) throws BaseVaselineClientException {
        try {
            api.delete(id);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    // crud validation
    protected Class<?>[] validationGroupsForSave() {
        return new Class<?>[]{IEntitySaveValidation.class};
    }

    protected Class<?>[] validationGroupsForUpdate() {
        return new Class<?>[]{IEntityUpdateValidation.class};
    }

    protected Class<?>[] validationGroupsForDelete() {
        return new Class<?>[]{IEntityDeleteValidation.class};
    }
}
