package ir.amv.os.vaseline.reporting.async.impl.server.base.crud;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.crud.api.BaseCrudApiImpl;
import ir.amv.os.vaseline.data.apis.dao.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.IBaseDoubleParameterCallback;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequestServer;
import ir.amv.os.vaseline.reporting.api.server.model.ICreateReportApi;
import ir.amv.os.vaseline.reporting.async.api.server.base.crud.IBaseReportingCrudAsyncApi;
import ir.amv.os.vaseline.reporting.async.impl.server.base.parent.BaseReportingAsyncApiImplHelper;
import ir.amv.os.vaseline.reporting.async.impl.server.base.ro.BaseReportingReadOnlyAsyncApiImplHelper;
import ir.amv.os.vaseline.security.authentication.api.shared.api.IAuthenticationApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

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
    public Long genericReport(CreateReportRequestServer request, IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback, IBaseDoubleParameterCallback<IBaseCallback<List<E>, Void>, PagingDto, Void> loadDataCallback) throws BaseVaselineServerException {
        return BaseReportingAsyncApiImplHelper.genericReport(this, request, createReportApi, authenticationApi, fileApi,
                getReportFileCategory(request), countDataCallback, loadDataCallback);
    }

    @Override
    public Long genericReport(CreateReportRequestServer request, IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback, IBaseCallback<IBaseCallback<IVaselineDataScroller<E>, Void>, Void> loadDataCallback) throws BaseVaselineServerException {
        return BaseReportingAsyncApiImplHelper.genericReport(this, request, createReportApi, authenticationApi, fileApi,
                getReportFileCategory(request), countDataCallback, loadDataCallback);
    }

    @Override
    public Class<E> getReportObjectClass() {
        return getEntityClass();
    }

    @Override
    public Long reportByExample(CreateReportRequestServer request, final D example) throws BaseVaselineServerException {
        return BaseReportingReadOnlyAsyncApiImplHelper.reportByExample(this, request, example, createReportApi, authenticationApi, fileApi, getReportFileCategory(request));
    }

    protected String getReportFileCategory(CreateReportRequestServer request) {
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
