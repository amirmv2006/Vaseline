package ir.amv.os.vaseline.reporting.async.api.server.base.ro;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.api.IBaseReadOnlyApi;
import ir.amv.os.vaseline.basics.apis.core.api.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequestServer;
import ir.amv.os.vaseline.reporting.async.api.server.base.parent.IBaseReportingAsyncApi;

import java.io.Serializable;

/**
 * Created by AMV on 2/13/2016.
 */
public interface IBaseReportingReadOnlyAsyncApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyApi<E, D, Id>, IBaseReportingAsyncApi<E> {

    Long reportByExample(CreateReportRequestServer request, D example) throws BaseVaselineServerException;

}
