package ir.amv.os.vaseline.reporting.api.server.model;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.api.IBaseApi;
import ir.amv.os.vaseline.reporting.api.server.datasource.BaseBeansDataSource;

import java.io.OutputStream;

/**
 * Created by AMV on 2/10/2016.
 */
public interface ICreateReportApi extends IBaseApi {

    void generateReport(CreateReportRequestServer reportSource, OutputStream outputStream,
                        BaseBeansDataSource<?> dataSource);
}
