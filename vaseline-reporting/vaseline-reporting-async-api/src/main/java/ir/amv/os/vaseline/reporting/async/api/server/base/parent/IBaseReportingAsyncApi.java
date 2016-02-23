package ir.amv.os.vaseline.reporting.async.api.server.base.parent;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.api.IBaseApi;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseDoubleParameterCallback;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequestServer;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by AMV on 2/13/2016.
 */
public interface IBaseReportingAsyncApi<E> extends IBaseApi {

    Future<Long> genericReport(CreateReportRequestServer request,
                               IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback,
                               IBaseDoubleParameterCallback<IBaseCallback<List<E>, Void>, PagingDto, Void> loadDataCallback) throws BaseVaselineServerException;

    Class<E> getReportObjectClass();
}
