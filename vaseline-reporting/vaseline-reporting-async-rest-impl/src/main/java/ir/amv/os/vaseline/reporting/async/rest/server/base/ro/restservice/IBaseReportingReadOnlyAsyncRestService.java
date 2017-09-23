package ir.amv.os.vaseline.reporting.async.rest.server.base.ro.restservice;

import ir.amv.os.vaseline.base.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.ws.rest.server.base.ro.IBaseReadOnlyRestService;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by AMV on 2/14/2016.
 */
public interface IBaseReportingReadOnlyAsyncRestService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyRestService<D, Id> {

    Long reportByExample(Map<String, Object> map) throws BaseVaselineServerException;
}
