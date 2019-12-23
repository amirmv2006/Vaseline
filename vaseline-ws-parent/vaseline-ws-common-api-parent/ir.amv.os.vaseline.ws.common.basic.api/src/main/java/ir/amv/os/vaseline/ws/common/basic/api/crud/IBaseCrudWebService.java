package ir.amv.os.vaseline.ws.common.basic.api.crud;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.ws.common.basic.api.ro.IBaseReadOnlyWebService;

import java.io.Serializable;

/**
 * @author Amir
 */
public interface IBaseCrudWebService<Id extends Serializable, D extends IBaseDto<Id>>
        extends IBaseReadOnlyWebService<Id, D> {

    Id save(D t) throws BaseExternalException;

    void update(D t) throws BaseExternalException;

    void delete(D id) throws BaseExternalException;
    void deleteById(Id id) throws BaseExternalException;
}
