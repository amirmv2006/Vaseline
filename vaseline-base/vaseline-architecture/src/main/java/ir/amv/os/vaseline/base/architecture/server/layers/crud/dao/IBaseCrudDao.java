package ir.amv.os.vaseline.base.architecture.server.layers.crud.dao;

import ir.amv.os.vaseline.base.architecture.server.layers.base.dao.IBaseDao;
import ir.amv.os.vaseline.base.architecture.server.layers.ro.dao.IBaseReadOnlyDao;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseCrudDao<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyDao<E, D, Id> {

    // CUD Operations
    Id save(E entity);

    void update(E entity);

    void delete(E entity);
    void deleteAll();

}
