package ir.amv.os.vaseline.data.dao.basic.api.ro;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;

import java.io.Serializable;

/**
 * @author Amir
 */
public interface IDefaultGenericReadOnlyRepository<I extends Serializable, E extends IBasePersistenceModel<I>>
        extends IBasePersistentModelRepository<I, E> {

    @Override
    default E newPersistentModel() throws IllegalAccessException, InstantiationException {
        return getPersistentModelClass().newInstance();
    }

}
