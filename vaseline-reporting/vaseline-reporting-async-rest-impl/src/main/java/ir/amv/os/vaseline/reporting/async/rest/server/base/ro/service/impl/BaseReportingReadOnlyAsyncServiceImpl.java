package ir.amv.os.vaseline.reporting.async.rest.server.base.ro.service.impl;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.service.BaseReadOnlyServiceImpl;
import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequestServer;
import ir.amv.os.vaseline.reporting.api.shared.model.CreateReportRequestClient;
import ir.amv.os.vaseline.reporting.async.api.server.base.ro.IBaseReportingReadOnlyAsyncApi;
import ir.amv.os.vaseline.reporting.async.rest.server.base.ro.service.IBaseReportingReadOnlyAsyncService;

import java.io.Serializable;

/**
 * Created by AMV on 2/14/2016.
 */
public class BaseReportingReadOnlyAsyncServiceImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, API extends IBaseReportingReadOnlyAsyncApi<E, D, Id>>
        extends BaseReadOnlyServiceImpl<E, D, Id, API>
        implements IBaseReportingReadOnlyAsyncService<D, Id>{

    @Override
    public Long reportByExample(CreateReportRequestClient request, D example) throws BaseVaselineServerException {
        CreateReportRequestServer requestServer = convert(request, CreateReportRequestServer.class, validationGroupsForReportRequest());
        return api.reportByExample(requestServer, example);
    }

    protected Class<?>[] validationGroupsForReportRequest() {
        return null;
    }
}
