package ir.amv.os.vaseline.reporting.api.server.model;

import ir.amv.os.vaseline.reporting.api.shared.enums.ReportOutputType;

import java.util.Map;

/**
 * Created by AMV on 2/10/2016.
 */
public class CreateReportRequestServer {

    private IBaseReportSourceServer reportSource;
    private ReportOutputType outputType;
    private Map<String, Object> argsMap;

    public IBaseReportSourceServer getReportSource() {
        return reportSource;
    }

    public void setReportSource(IBaseReportSourceServer reportSource) {
        this.reportSource = reportSource;
    }

    public ReportOutputType getOutputType() {
        return outputType;
    }

    public void setOutputType(ReportOutputType outputType) {
        this.outputType = outputType;
    }

    public Map<String, Object> getArgsMap() {
        return argsMap;
    }

    public void setArgsMap(Map<String, Object> argsMap) {
        this.argsMap = argsMap;
    }
}
