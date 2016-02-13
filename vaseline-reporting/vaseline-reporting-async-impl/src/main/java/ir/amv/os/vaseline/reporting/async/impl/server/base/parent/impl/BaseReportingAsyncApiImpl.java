package ir.amv.os.vaseline.reporting.async.impl.server.base.parent.impl;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.api.BaseApiImpl;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequest;
import ir.amv.os.vaseline.reporting.async.api.server.base.parent.IBaseReportingAsyncApi;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by AMV on 2/13/2016.
 */
public class BaseReportingAsyncApiImpl<E>
        extends BaseApiImpl
        implements IBaseReportingAsyncApi<E> {

    @Override
    public Future<Long> genericReport(CreateReportRequest request, IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback, IBaseCallback<IBaseCallback<List<E>, Void>, Void> loadDataCallback) {
        return null;
    }
}
