package ir.amv.os.vaseline.base.architecture.impl.server.layers.crud.service;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.ro.service.BaseReadOnlyServiceImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.crud.api.IBaseCrudApi;
import ir.amv.os.vaseline.base.architecture.server.layers.crud.service.IBaseCrudService;
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
            E ent = convertDtoToEntity(t);
            Id id = api.save(ent);
            return id;
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public void update(D t) throws BaseVaselineClientException {
        try {
            E entity = convertDtoToEntity(t);
            api.update(entity);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public void delete(D id) throws BaseVaselineClientException {
        try {
            E entity = convertDtoToEntity(id);
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
}
