package ir.amv.os.vaseline.data.basic.api.base;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;

import java.io.Serializable;
import java.util.List;

public interface IBaseCrudRepository<I extends Serializable, E extends IBasePersistenceModel<I>>
        extends IBaseReadOnlyRepository<I, E> {


    <F extends E> F save(F model);

    <F extends E> List<F> saveAll(Iterable<F> models);

    void delete(E model);

    void deleteAll(Iterable<? extends E> models);
}
