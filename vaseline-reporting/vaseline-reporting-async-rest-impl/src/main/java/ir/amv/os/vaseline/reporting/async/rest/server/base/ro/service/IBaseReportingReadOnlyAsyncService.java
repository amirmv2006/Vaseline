package ir.amv.os.vaseline.reporting.async.rest.server.base.ro.service;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.service.IBaseReadOnlyService;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequest;

import java.io.Serializable;
import java.util.concurrent.Future;

/**
 * Created by AMV on 2/14/2016.
 */
public interface IBaseReportingReadOnlyAsyncService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyService<D, Id> {

    Long reportByExample(CreateReportRequest request, D example) throws BaseVaselineServerException;
}
