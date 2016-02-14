package ir.amv.os.vaseline.reporting.async.rest.server.base.ro.restservice.impl;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.service.IBaseReadOnlyService;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequest;
import ir.amv.os.vaseline.reporting.async.rest.server.base.ro.restservice.IBaseReportingReadOnlyAsyncRestService;
import ir.amv.os.vaseline.reporting.async.rest.server.base.ro.service.IBaseReportingReadOnlyAsyncService;
import ir.amv.os.vaseline.ws.rest.server.base.ro.impl.BaseReadOnlyRestServiceImpl;

import java.io.Serializable;

/**
 * Created by AMV on 2/14/2016.
 */
public class BaseReportingReadOnlyAsyncRestServiceImpl<D extends IBaseDto<Id>, Id extends Serializable, S extends IBaseReportingReadOnlyAsyncService<D, Id>>
        extends BaseReadOnlyRestServiceImpl<D, Id, S>
        implements IBaseReportingReadOnlyAsyncRestService<D, Id> {

    @Override
    public Long reportByExample(CreateReportRequest request, D example) throws BaseVaselineServerException {
        return service.reportByExample(request, example);
    }
}
