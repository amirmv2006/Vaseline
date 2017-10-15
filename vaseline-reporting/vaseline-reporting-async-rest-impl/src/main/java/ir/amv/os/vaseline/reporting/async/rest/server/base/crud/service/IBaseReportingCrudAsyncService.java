package ir.amv.os.vaseline.reporting.async.rest.server.base.crud.service;

import ir.amv.os.vaseline.service.apis.layer.server.crud.IBaseCrudService;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.async.rest.server.base.ro.service.IBaseReportingReadOnlyAsyncService;

import java.io.Serializable;

/**
 * Created by AMV on 2/14/2016.
 */
public interface IBaseReportingCrudAsyncService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReportingReadOnlyAsyncService<D, Id>, IBaseCrudService<D, Id> {
}
