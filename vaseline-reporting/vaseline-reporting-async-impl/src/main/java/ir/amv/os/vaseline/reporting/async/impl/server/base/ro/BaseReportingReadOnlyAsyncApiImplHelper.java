package ir.amv.os.vaseline.reporting.async.impl.server.base.ro;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.api.IBaseReadOnlyApi;
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
import ir.amv.os.vaseline.reporting.async.impl.server.base.parent.BaseReportingAsyncApiImplHelper;
import ir.amv.os.vaseline.security.shared.api.IAuthenticationApi;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by AMV on 2/14/2016.
 */
public class BaseReportingReadOnlyAsyncApiImplHelper {

    private BaseReportingReadOnlyAsyncApiImplHelper() {
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> Future<Long> reportByExample(
            final IBaseReadOnlyApi<E, D, Id> api,
            CreateReportRequest request,
            final D example,
            ICreateReportApi createReportApi,
            IAuthenticationApi authenticationApi,
            IFileApi fileApi,
            String reportFileCategory)
            throws BaseVaselineServerException {
        return BaseReportingAsyncApiImplHelper.genericReport(request, createReportApi, authenticationApi, fileApi, reportFileCategory,
                new BaseCallbackImpl<IBaseCallback<Integer, Void>, Void>() {
                    @Override
                    public void onSuccess(IBaseCallback<Integer, Void> result) {
                        try {
                            result.onSuccess(api.countByExample(example).intValue());
                        } catch (BaseVaselineServerException e) {
                            e.printStackTrace();
                        }
                    }
                }, new BaseDoubleParameterCallbackImpl<IBaseCallback<List<E>, Void>, PagingDto, Void>() {
                    @Override
                    public void onSuccess(IBaseCallback<List<E>, Void> firstParam, PagingDto secondParameter) {
                        try {
                            firstParam.onSuccess(api.searchByExample(example, secondParameter));
                        } catch (BaseVaselineServerException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
}
