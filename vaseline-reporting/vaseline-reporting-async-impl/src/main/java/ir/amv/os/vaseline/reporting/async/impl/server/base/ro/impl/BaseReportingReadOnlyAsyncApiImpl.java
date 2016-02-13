package ir.amv.os.vaseline.reporting.async.impl.server.base.ro.impl;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.api.BaseReadOnlyApiImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.IBaseReadOnlyDao;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequest;
import ir.amv.os.vaseline.reporting.async.api.server.base.ro.IBaseReportingReadOnlyAsyncApi;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by AMV on 2/13/2016.
 */
public class BaseReportingReadOnlyAsyncApiImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, DAO extends IBaseReadOnlyDao<E, D, Id>>
        extends BaseReadOnlyApiImpl<E, D, Id, DAO>
        implements IBaseReportingReadOnlyAsyncApi<E, D, Id> {

    @Override
    public Future<Long> genericReport(CreateReportRequest request, IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback, IBaseCallback<IBaseCallback<List<E>, Void>, Void> loadDataCallback) {
        return null;
    }

    @Override
    public Future<Long> reportByExample(CreateReportRequest request, D example, PagingDto pagingDto) throws BaseVaselineServerException {
        return null;
    }
}
