package ir.amv.os.vaseline.service.basic.api.server.crud;

import ir.amv.os.vaseline.service.basic.api.server.ro.IBaseReadOnlyService;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;

import java.io.Serializable;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseCrudService<I extends Serializable, D extends IBaseDto<I>>
        extends IBaseReadOnlyService<I, D> {

    I save(D t) throws BaseVaselineClientException;

    void update(D t) throws BaseVaselineClientException;

    void delete(D id) throws BaseVaselineClientException;
    void deleteById(I id) throws BaseVaselineClientException;
}
