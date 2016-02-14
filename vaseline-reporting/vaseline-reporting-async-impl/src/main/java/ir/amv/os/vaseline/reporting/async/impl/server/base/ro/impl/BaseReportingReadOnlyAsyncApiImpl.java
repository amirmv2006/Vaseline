package ir.amv.os.vaseline.reporting.async.impl.server.base.ro.impl;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.api.BaseReadOnlyApiImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.IBaseReadOnlyDao;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseDoubleParameterCallback;
import ir.amv.os.vaseline.base.core.shared.util.callback.impl.BaseCallbackImpl;
import ir.amv.os.vaseline.base.core.shared.util.callback.impl.BaseDoubleParameterCallbackImpl;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequest;
import ir.amv.os.vaseline.reporting.api.server.model.ICreateReportApi;
import ir.amv.os.vaseline.reporting.async.api.server.base.ro.IBaseReportingReadOnlyAsyncApi;
import ir.amv.os.vaseline.reporting.async.impl.server.base.parent.impl.BaseReportingAsyncApiImplHelper;
import ir.amv.os.vaseline.security.shared.api.IAuthenticationApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by AMV on 2/13/2016.
 */
public class BaseReportingReadOnlyAsyncApiImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, DAO extends IBaseReadOnlyDao<E, D, Id>>
        extends BaseReadOnlyApiImpl<E, D, Id, DAO>
        implements IBaseReportingReadOnlyAsyncApi<E, D, Id> {

    private ICreateReportApi createReportApi;
    private IAuthenticationApi authenticationApi;
    private IFileApi fileApi;

    @Override
    public Future<Long> genericReport(CreateReportRequest request, IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback, IBaseDoubleParameterCallback<IBaseCallback<List<E>, Void>, PagingDto, Void> loadDataCallback) throws BaseVaselineServerException {
        return BaseReportingAsyncApiImplHelper.genericReport(request, createReportApi, authenticationApi, fileApi,
                getReportFileCategory(request), countDataCallback, loadDataCallback);
    }

    @Override
    @Async
    public Future<Long> reportByExample(CreateReportRequest request, final D example) throws BaseVaselineServerException {
        return BaseReportingAsyncApiImplHelper.genericReport(request, createReportApi, authenticationApi, fileApi,
                getReportFileCategory(request), new BaseCallbackImpl<IBaseCallback<Integer, Void>, Void>() {
                    @Override
                    public void onSuccess(IBaseCallback<Integer, Void> result) {
                        try {
                            result.onSuccess(countByExample(example).intValue());
                        } catch (BaseVaselineServerException e) {
                            e.printStackTrace();
                        }
                    }
                }, new BaseDoubleParameterCallbackImpl<IBaseCallback<List<E>, Void>, PagingDto, Void>() {
                    @Override
                    public void onSuccess(IBaseCallback<List<E>, Void> firstParam, PagingDto secondParameter) {
                        try {
                            firstParam.onSuccess(searchByExample(example, secondParameter));
                        } catch (BaseVaselineServerException e) {
                            e.printStackTrace();
                        }
                    }
                });
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
