package ir.amv.os.vaseline.data.apis.dao.server.crud;

import ir.amv.os.vaseline.data.apis.dao.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseCrudDao<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyDao<E, Id> {

    // CUD Operations
    Id save(E entity);

    void update(E entity);

    void delete(E entity);

}
