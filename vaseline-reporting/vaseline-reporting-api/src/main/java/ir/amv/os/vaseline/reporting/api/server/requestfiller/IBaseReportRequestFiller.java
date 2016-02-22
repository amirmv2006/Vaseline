package ir.amv.os.vaseline.reporting.api.server.requestfiller;

import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequestServer;
import ir.amv.os.vaseline.reporting.api.server.model.ICreateReportApi;

/**
 * Created by AMV on 2/22/2016.
 */
public interface IBaseReportRequestFiller {

    CreateReportRequestServer fillReportRequest(CreateReportRequestServer reportRequestServer, ICreateReportApi createReportApi);
}
