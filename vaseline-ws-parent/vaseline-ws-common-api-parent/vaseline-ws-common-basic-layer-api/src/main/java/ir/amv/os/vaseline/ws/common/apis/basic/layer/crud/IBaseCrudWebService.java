package ir.amv.os.vaseline.ws.common.apis.basic.layer.crud;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.common.apis.basic.layer.ro.IBaseReadOnlyWebService;

import java.io.Serializable;

/**
 * @author Amir
 */
public interface IBaseCrudWebService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyWebService<D, Id> {

    Id save(D t) throws BaseVaselineClientException;

    void update(D t) throws BaseVaselineClientException;

    void delete(D id) throws BaseVaselineClientException;
    void deleteById(Id id) throws BaseVaselineClientException;
}
