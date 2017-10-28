package ir.amv.os.vaseline.reporting.async.impl.server.base.parent;

import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.dao.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.business.apis.layer.server.base.IBaseApi;
import ir.amv.os.vaseline.basics.apis.core.shared.util.date.DateUtil;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.IBaseDoubleParameterCallback;
import ir.amv.os.vaseline.basics.apis.core.shared.util.pager.defimpl.DefaultAsyncListPager;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.file.api.server.model.base.IFileEntity;
import ir.amv.os.vaseline.reporting.api.server.datasource.PaginatorDataSource;
import ir.amv.os.vaseline.reporting.api.server.datasource.ScrollerDataSource;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequestServer;
import ir.amv.os.vaseline.reporting.api.server.model.ICreateReportApi;
import ir.amv.os.vaseline.reporting.api.server.requestfiller.IBaseReportRequestFiller;
import ir.amv.os.vaseline.reporting.async.api.server.base.parent.IBaseReportingAsyncApi;
import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by AMV on 2/13/2016.
 */
public class BaseReportingAsyncApiImplHelper {

    private BaseReportingAsyncApiImplHelper() {
    }

    public static <E> Long genericReport(
            IBaseApi api,
            CreateReportRequestServer request,
            ICreateReportApi createReportApi,
            IAuthenticationApi authenticationApi,
            IFileApi fileApi,
            String reportFileCategory,
            IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback,
            IBaseDoubleParameterCallback<IBaseCallback<List<E>, Void>, PagingDto, Void> loadDataCallback)
            throws BaseVaselineServerException {
        request = fillRepReq(request, api, authenticationApi);
        final DefaultAsyncListPager<E> asyncListPager = new DefaultAsyncListPager<E>();
        asyncListPager.setCountDataCallback(countDataCallback);
        asyncListPager.setLoadDataCallback(loadDataCallback);
        PaginatorDataSource<E> dataSource = new PaginatorDataSource<E>(asyncListPager);
        return doGenerateReport(createReportApi, authenticationApi, fileApi, reportFileCategory, request, dataSource);
    }

    public static <E> Long genericReport(
            IBaseApi api,
            CreateReportRequestServer request,
            ICreateReportApi createReportApi,
            IAuthenticationApi authenticationApi,
            IFileApi fileApi,
            String reportFileCategory,
            IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback,
            IBaseCallback<IBaseCallback<IVaselineDataScroller<E>, Void>, Void> loadDataCallback)
            throws BaseVaselineServerException {
        request = fillRepReq(request, api, authenticationApi);
        ScrollerDataSource<E> dataSource = new ScrollerDataSource<E>(loadDataCallback, countDataCallback);
        return doGenerateReport(createReportApi, authenticationApi, fileApi, reportFileCategory, request, dataSource);
    }

    private static <E> Long doGenerateReport(
            ICreateReportApi createReportApi,
            IAuthenticationApi authenticationApi,
            IFileApi fileApi,
            String reportFileCategory,
            CreateReportRequestServer request,
            JRDataSource dataSource) throws BaseVaselineServerException {
        try {
            File repResTmpFile = File.createTempFile("reportOutput", ".tmp");
            Future<Void> voidFuture = createReportApi.generateReport(request,
                    new FileOutputStream(repResTmpFile),
                    dataSource);
            voidFuture.get();
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
            return fileId;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <Id extends Serializable, DAO extends IBaseReadOnlyDao<?, Id>> String getFileNameFor(
            IAuthenticationApi authenticationApi,
            CreateReportRequestServer request) throws BaseVaselineServerException {
        StringBuffer fileName = new StringBuffer();
        fileName.append(authenticationApi.getCurrentUsername());
        fileName.append("-");
        fileName.append(DateUtil.toString(DateUtil.newJalaliCalendar()));
        fileName.append(".");
        fileName.append(request.getOutputType().fileSuffix());
        return fileName.toString();
    }

    private static CreateReportRequestServer fillRepReq(CreateReportRequestServer request, IBaseApi api, IAuthenticationApi authenticationApi) throws BaseVaselineServerException {
        String currentUsername = authenticationApi.getCurrentUsername();
        if (request.getArgsMap() != null) {
            request.setArgsMap(new HashMap<String, Object>());
        }
        request.getArgsMap().put("currentUser", currentUsername);
        for (IBaseReportRequestFiller reportRequestFiller : reportRequestFillers) {
            request = reportRequestFiller.fillReportRequest(request, api);
        }
        return request;
    }

    public static <E> String getReportFileCategory(IBaseReportingAsyncApi<E> api, CreateReportRequestServer request) {
        return "report";
    }

    private static List<IBaseReportRequestFiller> reportRequestFillers = new ArrayList<IBaseReportRequestFiller>();
    public static void addReportRequestFiller(IBaseReportRequestFiller reportRequestFiller) {
        reportRequestFillers.add(reportRequestFiller);
    }
}
