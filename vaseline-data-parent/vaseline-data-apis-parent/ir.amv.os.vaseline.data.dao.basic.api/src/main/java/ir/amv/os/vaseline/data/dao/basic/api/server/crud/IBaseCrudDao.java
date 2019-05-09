package ir.amv.os.vaseline.data.dao.basic.api.server.crud;

import ir.amv.os.vaseline.data.dao.basic.api.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;

import java.io.Serializable;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseCrudDao<I extends Serializable, E extends IBaseEntity<I>>
        extends IBaseReadOnlyDao<I, E> {

    // CUD Operations
    I save(E entity);

    void update(E entity);

    void delete(E entity);

}
