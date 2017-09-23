package ir.amv.os.vaseline.reporting.async.api.server.base.crud;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.api.IBaseCrudApi;
import ir.amv.os.vaseline.base.core.api.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.async.api.server.base.ro.IBaseReportingReadOnlyAsyncApi;

import java.io.Serializable;

/**
 * Created by AMV on 2/14/2016.
 */
public interface IBaseReportingCrudAsyncApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReportingReadOnlyAsyncApi<E, D, Id>, IBaseCrudApi<E, D, Id> {
}
