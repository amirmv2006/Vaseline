package ir.amv.os.vaseline.reporting.async.impl.server.base.parent;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.api.BaseApiImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseDoubleParameterCallback;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequestServer;
import ir.amv.os.vaseline.reporting.api.server.model.ICreateReportApi;
import ir.amv.os.vaseline.reporting.async.api.server.base.parent.IBaseReportingAsyncApi;
import ir.amv.os.vaseline.security.authentication.api.shared.api.IAuthenticationApi;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by AMV on 2/13/2016.
 */
public class BaseReportingAsyncApiImpl<E>
        extends BaseApiImpl
        implements IBaseReportingAsyncApi<E> {

    private ICreateReportApi createReportApi;
    private IAuthenticationApi authenticationApi;
    private IFileApi fileApi;
    private Class<E> reportObjectClass;

    public BaseReportingAsyncApiImpl() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass());
        if (genericArgumentClasses != null) {
            reportObjectClass = (Class<E>) genericArgumentClasses[0];
        }
    }

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
        return reportObjectClass;
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
