package ir.amv.os.vaseline.reporting.api.server.model;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.api.IBaseApi;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.OutputStream;
import java.util.concurrent.Future;

/**
 * Created by AMV on 2/10/2016.
 */
public interface ICreateReportApi extends IBaseApi {

    Future<Void> generateReport(CreateReportRequestServer reportSource, OutputStream outputStream,
                                JRDataSource dataSource);
}
