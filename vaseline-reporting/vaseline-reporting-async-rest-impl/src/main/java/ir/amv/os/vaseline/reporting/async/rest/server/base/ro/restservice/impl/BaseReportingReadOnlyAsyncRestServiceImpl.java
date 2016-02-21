package ir.amv.os.vaseline.reporting.async.rest.server.base.ro.restservice.impl;

import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.api.shared.model.CreateReportRequestClient;
import ir.amv.os.vaseline.reporting.async.rest.server.base.ro.restservice.IBaseReportingReadOnlyAsyncRestService;
import ir.amv.os.vaseline.reporting.async.rest.server.base.ro.service.IBaseReportingReadOnlyAsyncService;
import ir.amv.os.vaseline.ws.rest.server.base.ro.impl.BaseReadOnlyRestServiceImpl;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by AMV on 2/14/2016.
 */
public class BaseReportingReadOnlyAsyncRestServiceImpl<D extends IBaseDto<Id>, Id extends Serializable, S extends IBaseReportingReadOnlyAsyncService<D, Id>>
        extends BaseReadOnlyRestServiceImpl<D, Id, S>
        implements IBaseReportingReadOnlyAsyncRestService<D, Id> {

    @Override
    public Long reportByExample(Map<String, Object> map) throws BaseVaselineServerException {
        return service.reportByExample((CreateReportRequestClient) map
                .get("request"), (D) map.get("example"));
    }
}
