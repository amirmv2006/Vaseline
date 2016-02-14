package ir.amv.os.vaseline.reporting.async.rest.server.base.crud.service.impl;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.crud.service.BaseCrudServiceImpl;
import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.service.BaseReadOnlyServiceImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.api.IBaseCrudApi;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequest;
import ir.amv.os.vaseline.reporting.async.api.server.base.crud.IBaseReportingCrudAsyncApi;
import ir.amv.os.vaseline.reporting.async.rest.server.base.crud.service.IBaseReportingCrudAsyncService;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by AMV on 2/14/2016.
 */
public class BaseReportingCrudAsyncServiceImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, API extends IBaseReportingCrudAsyncApi<E, D, Id>>
        extends BaseCrudServiceImpl<E, D, Id, API>
        implements IBaseReportingCrudAsyncService<D, Id> {

    @Override
    public Long reportByExample(CreateReportRequest request, D example) throws BaseVaselineServerException {
        Future<Long> longFuture = api.reportByExample(request, example);
        try {
            return longFuture.get();
        } catch (InterruptedException e) {
            throw new BaseVaselineServerException(e);
        } catch (ExecutionException e) {
            throw new BaseVaselineServerException(e);
        }
    }
}
