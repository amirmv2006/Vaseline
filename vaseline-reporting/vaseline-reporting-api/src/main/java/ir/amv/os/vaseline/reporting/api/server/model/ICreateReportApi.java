package ir.amv.os.vaseline.reporting.api.server.model;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.api.IBaseApi;
import ir.amv.os.vaseline.reporting.api.server.datasource.BaseBeansDataSource;
import ir.amv.os.vaseline.reporting.api.shared.enums.ReportOutputType;

import java.io.OutputStream;
import java.util.Map;

/**
 * Created by AMV on 2/10/2016.
 */
public interface ICreateReportApi extends IBaseApi {

    void generateReport(CreateReportRequest reportSource, OutputStream outputStream,
                        BaseBeansDataSource<?> dataSource);
}
