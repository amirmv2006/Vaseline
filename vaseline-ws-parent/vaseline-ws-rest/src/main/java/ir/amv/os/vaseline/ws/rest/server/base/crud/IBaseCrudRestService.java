package ir.amv.os.vaseline.ws.rest.server.base.crud;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.rest.server.base.ro.IBaseReadOnlyRestService;

import java.io.Serializable;

/**
 * Created by AMV on 2/13/2016.
 */
public interface IBaseCrudRestService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyRestService<D, Id> {

    Id save(D t) throws BaseVaselineClientException;

    void update(D t) throws BaseVaselineClientException;

    void delete(D id) throws BaseVaselineClientException;
    void deleteById(Id id) throws BaseVaselineClientException;
}
