package ir.amv.os.vaseline.reporting.async.impl.server.base.parent.impl;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.api.BaseApiImpl;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseDoubleParameterCallback;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequest;
import ir.amv.os.vaseline.reporting.api.server.model.ICreateReportApi;
import ir.amv.os.vaseline.reporting.async.api.server.base.parent.IBaseReportingAsyncApi;
import ir.amv.os.vaseline.security.shared.api.IAuthenticationApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by AMV on 2/13/2016.
 */
public class BaseReportingAsyncApiImpl<E>
        extends BaseApiImpl
        implements IBaseReportingAsyncApi<E> {

    private ICreateReportApi createReportApi;
    private IAuthenticationApi authenticationApi;
    private IFileApi fileApi;

    @Override
    public Future<Long> genericReport(CreateReportRequest request, IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback, IBaseDoubleParameterCallback<IBaseCallback<List<E>, Void>, PagingDto, Void> loadDataCallback) throws BaseVaselineServerException {
        return BaseReportingAsyncApiImplHelper.genericReport(request, createReportApi, authenticationApi, fileApi,
                getReportFileCategory(request), countDataCallback, loadDataCallback);
    }

    private String getReportFileCategory(CreateReportRequest request) {
        return "report";
    }

    @Autowired
    public void setCreateReportApi(ICreateReportApi createReportApi) {
        this.createReportApi = createReportApi;
    }

    @Autowired
    public void setAuthenticationApi(IAuthenticationApi authenticationApi) {
        this.authenticationApi = authenticationApi;
    }

    @Autowired
    public void setFileApi(IFileApi fileApi) {
        this.fileApi = fileApi;
    }
}
