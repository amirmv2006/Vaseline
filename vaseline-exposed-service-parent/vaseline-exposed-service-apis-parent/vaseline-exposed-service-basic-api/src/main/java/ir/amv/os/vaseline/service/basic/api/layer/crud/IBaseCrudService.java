package ir.amv.os.vaseline.service.basic.api.layer.crud;

import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.api.layer.ro.IBaseReadOnlyService;

import java.io.Serializable;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseCrudService<I extends Serializable, D extends IBaseDto<I>>
        extends IBaseReadOnlyService<I, D> {

    I save(D t) throws BaseExternalException;

    void update(D t) throws BaseExternalException;

    void delete(D id) throws BaseExternalException;
    void deleteById(I id) throws BaseExternalException;
}
