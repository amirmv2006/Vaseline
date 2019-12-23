package ir.amv.os.vaseline.service.basic.api.server.crud;

import ir.amv.os.vaseline.service.basic.api.server.ro.IBaseReadOnlyService;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;

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
