package ir.amv.os.vaseline.ws.common.basic.api.crud;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.common.basic.api.ro.IBaseReadOnlyWebService;

import java.io.Serializable;

/**
 * @author Amir
 */
public interface IBaseCrudWebService<Id extends Serializable, D extends IBaseDto<Id>>
        extends IBaseReadOnlyWebService<Id, D> {

    Id save(D t) throws BaseVaselineClientException;

    void update(D t) throws BaseVaselineClientException;

    void delete(D id) throws BaseVaselineClientException;
    void deleteById(Id id) throws BaseVaselineClientException;
}
