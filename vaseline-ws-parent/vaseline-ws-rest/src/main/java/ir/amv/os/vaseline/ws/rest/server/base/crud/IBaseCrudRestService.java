package ir.amv.os.vaseline.ws.rest.server.base.crud;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.ws.rest.server.base.ro.IBaseReadOnlyRestService;

import java.io.Serializable;

/**
 * Created by AMV on 2/13/2016.
 */
public interface IBaseCrudRestService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyRestService<D, Id> {

    Id save(D t) throws BaseExternalException;

    void update(D t) throws BaseExternalException;

    void delete(D id) throws BaseExternalException;
    void deleteById(Id id) throws BaseExternalException;
}
