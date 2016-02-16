package ir.amv.os.vaseline.reporting.async.impl.server.base.crud;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.crud.api.BaseCrudApiImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.IBaseCrudDao;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseDoubleParameterCallback;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequest;
import ir.amv.os.vaseline.reporting.api.server.model.ICreateReportApi;
import ir.amv.os.vaseline.reporting.async.api.server.base.crud.IBaseReportingCrudAsyncApi;
import ir.amv.os.vaseline.reporting.async.impl.server.base.parent.BaseReportingAsyncApiImplHelper;
import ir.amv.os.vaseline.reporting.async.impl.server.base.ro.BaseReportingReadOnlyAsyncApiImplHelper;
import ir.amv.os.vaseline.security.authentication.api.shared.api.IAuthenticationApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by AMV on 2/14/2016.
 */
public class BaseReportingCrudAsyncApiImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, DAO extends IBaseCrudDao<E, D, Id>>
        extends BaseCrudApiImpl<E, D, Id, DAO>
        implements IBaseReportingCrudAsyncApi<E, D, Id>{

    private ICreateReportApi createReportApi;
    private IAuthenticationApi authenticationApi;
    private IFileApi fileApi;

    @Override
    @Async
    public Future<Long> genericReport(CreateReportRequest request, IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback, IBaseDoubleParameterCallback<IBaseCallback<List<E>, Void>, PagingDto, Void> loadDataCallback) throws BaseVaselineServerException {
        return BaseReportingAsyncApiImplHelper.genericReport(request, createReportApi, authenticationApi, fileApi,
                getReportFileCategory(request), countDataCallback, loadDataCallback);
    }

    @Override
    @Async
    public Future<Long> reportByExample(CreateReportRequest request, final D example) throws BaseVaselineServerException {
        return BaseReportingReadOnlyAsyncApiImplHelper.reportByExample(this, request, example, createReportApi, authenticationApi, fileApi, getReportFileCategory(request));
    }

    protected String getReportFileCategory(CreateReportRequest request) {
        return BaseReportingAsyncApiImplHelper.getReportFileCategory(this, request);
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
