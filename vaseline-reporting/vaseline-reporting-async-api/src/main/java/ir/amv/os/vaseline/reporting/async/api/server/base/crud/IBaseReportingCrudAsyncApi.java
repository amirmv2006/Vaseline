package ir.amv.os.vaseline.reporting.async.api.server.base.crud;

import ir.amv.os.vaseline.business.apis.layer.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.async.api.server.base.ro.IBaseReportingReadOnlyAsyncApi;

import java.io.Serializable;

/**
 * Created by AMV on 2/14/2016.
 */
public interface IBaseReportingCrudAsyncApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReportingReadOnlyAsyncApi<E, D, Id>, IBaseCrudApi<E, D, Id> {
}
