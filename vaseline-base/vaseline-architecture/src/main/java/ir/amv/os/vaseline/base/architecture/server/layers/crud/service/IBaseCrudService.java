package ir.amv.os.vaseline.base.architecture.server.layers.crud.service;

import ir.amv.os.vaseline.base.architecture.server.layers.ro.service.IBaseReadOnlyService;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.exc.BaseVaselineClientException;

import java.io.Serializable;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseCrudService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyService<D, Id> {

    Id save(D t) throws BaseVaselineClientException;

    boolean update(D t) throws BaseVaselineClientException;

    boolean delete(D id) throws BaseVaselineClientException;
    boolean deleteById(Id id) throws BaseVaselineClientException;
}
