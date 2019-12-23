package ir.amv.os.vaseline.data.dao.basic.api.ro;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.data.dao.basic.api.base.IBaseRepository;

import java.io.Serializable;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBasePersistentModelRepository<I extends Serializable, E extends IBasePersistenceModel<I>>
        extends IBaseRepository {

    Class<? extends E> getPersistentModelClass();
    E newPersistentModel() throws IllegalAccessException, InstantiationException;
}
