package ir.amv.os.vaseline.business.basic.api.layer.crud;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseCrudApi<I extends Serializable, E extends IBaseBusinessModel<I>>
        extends IBaseReadOnlyApi<I, E>, IBaseModelWriteApi<E> {

    I save(E entity) throws BaseBusinessException;
    List<I> saveBatch(List<E> entities) throws BaseBusinessException;

    void update(E entity) throws BaseBusinessException;
    void updateBatch(List<E> entities) throws BaseBusinessException;

    void delete(E entity) throws BaseBusinessException;
    void deleteBatch(List<E> entities) throws BaseBusinessException;
    void delete(I id) throws BaseBusinessException;

}
