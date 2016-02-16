package ir.amv.os.vaseline.reporting.async.impl.server.base.parent;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.IBaseReadOnlyDao;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseDoubleParameterCallback;
import ir.amv.os.vaseline.base.core.shared.util.date.DateUtil;
import ir.amv.os.vaseline.base.core.shared.util.pager.impl.DefaultAsyncListPager;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.file.api.server.model.base.IFileEntity;
import ir.amv.os.vaseline.reporting.api.server.datasource.BaseBeansDataSource;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequest;
import ir.amv.os.vaseline.reporting.api.server.model.ICreateReportApi;
import ir.amv.os.vaseline.reporting.async.api.server.base.parent.IBaseReportingAsyncApi;
import ir.amv.os.vaseline.security.authentication.api.shared.api.IAuthenticationApi;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.*;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by AMV on 2/13/2016.
 */
public class BaseReportingAsyncApiImplHelper {

    private BaseReportingAsyncApiImplHelper() {
    }

    public static <E> Future<Long> genericReport(
            CreateReportRequest request,
            ICreateReportApi createReportApi,
            IAuthenticationApi authenticationApi,
            IFileApi fileApi,
            String reportFileCategory,
            IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback,
            IBaseDoubleParameterCallback<IBaseCallback<List<E>, Void>, PagingDto, Void> loadDataCallback)
            throws BaseVaselineServerException {
        fillRepReq(request);
        final DefaultAsyncListPager<E> asyncListPager = new DefaultAsyncListPager<E>();
        asyncListPager.setCountDataCallback(countDataCallback);
        asyncListPager.setLoadDataCallback(loadDataCallback);
        BaseBeansDataSource<E> dataSource = new BaseBeansDataSource<E>(asyncListPager);
        return doGenerateReport(createReportApi, authenticationApi, fileApi, reportFileCategory, request, dataSource);
    }

    private static <E> Future<Long> doGenerateReport(
            ICreateReportApi createReportApi,
            IAuthenticationApi authenticationApi,
            IFileApi fileApi,
            String reportFileCategory,
            CreateReportRequest request,
            BaseBeansDataSource<E> dataSource) throws BaseVaselineServerException {
        try {
            File repResTmpFile = File.createTempFile("reportOutput", ".tmp");
            createReportApi.generateReport(request,
                    new FileOutputStream(repResTmpFile),
                    dataSource);
            IFileEntity fileEntity = fileApi.createFile(reportFileCategory);
            fileEntity.setCategory(reportFileCategory);
            fileEntity.setContentType(request.getOutputType()
                    .getContentType());
            fileEntity.setFileName(getFileNameFor(authenticationApi, request));
            fileEntity.setOwner(authenticationApi.getCurrentUsername());
            InputStream inputStream = new FileInputStream(repResTmpFile);
            fileEntity.setFileSize((long) inputStream.available());
            Long fileId = fileApi.uploadFile(reportFileCategory, fileEntity, inputStream);
            inputStream.close();
            return new AsyncResult<Long>(fileId);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    private static <Id extends Serializable, DAO extends IBaseReadOnlyDao<?, ?, Id>> String getFileNameFor(
            IAuthenticationApi authenticationApi,
            CreateReportRequest request) throws BaseVaselineServerException {
        StringBuffer fileName = new StringBuffer();
        fileName.append(authenticationApi.getCurrentUsername());
        fileName.append("-");
        fileName.append(DateUtil.toString(DateUtil.newJalaliCalendar()));
        fileName.append(".");
        fileName.append(request.getOutputType().fileSuffix());
        return fileName.toString();
    }

    private static void fillRepReq(CreateReportRequest request) {
    }

    public static <E> String getReportFileCategory(IBaseReportingAsyncApi<E> api, CreateReportRequest request) {
        return "report";
    }
}
