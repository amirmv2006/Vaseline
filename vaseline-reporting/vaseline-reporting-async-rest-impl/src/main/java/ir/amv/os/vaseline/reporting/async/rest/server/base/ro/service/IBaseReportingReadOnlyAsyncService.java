package ir.amv.os.vaseline.reporting.async.rest.server.base.ro.service;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.service.IBaseReadOnlyService;
import ir.amv.os.vaseline.base.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.api.shared.model.CreateReportRequestClient;

import java.io.Serializable;

/**
 * Created by AMV on 2/14/2016.
 */
public interface IBaseReportingReadOnlyAsyncService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyService<D, Id> {

    Long reportByExample(CreateReportRequestClient request, D example) throws BaseVaselineServerException;
}
