package ir.amv.os.vaseline.reporting.async.impl.server.base.ro;

import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.business.apis.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.CachingCallback;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequestServer;
import ir.amv.os.vaseline.reporting.api.server.model.ICreateReportApi;
import ir.amv.os.vaseline.reporting.async.impl.server.base.parent.BaseReportingAsyncApiImplHelper;
import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;

import java.io.Serializable;

/**
 * Created by AMV on 2/14/2016.
 */
public class BaseReportingReadOnlyAsyncApiImplHelper {

    private BaseReportingReadOnlyAsyncApiImplHelper() {
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> Long reportByExample(
            final IBaseReadOnlyApi<E, Id> api,
            CreateReportRequestServer request,
            final D example,
            ICreateReportApi createReportApi,
            IAuthenticationApi authenticationApi,
            IFileApi fileApi,
            String reportFileCategory)
            throws BaseVaselineServerException {
        return BaseReportingAsyncApiImplHelper.genericReport(api, request, createReportApi, authenticationApi, fileApi, reportFileCategory,
                new CachingCallback<Integer>() {
                    @Override
                    public Integer fetchValue() {
                        try {
                            return api.getProxy(IBaseReadOnlyApi.class).countAllApproximately().intValue();
                        } catch (BaseVaselineServerException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }, new BaseCallbackImpl<IBaseCallback<IVaselineDataScroller<D>,Void>, Void>() {
                    @Override
                    public void onSuccess(IBaseCallback<IVaselineDataScroller<D>, Void> result) {
                        try {
                            result.onSuccess(api.getProxy(IBaseReadOnlyApi.class).scrollByExample(example));
                        } catch (BaseVaselineServerException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
}
