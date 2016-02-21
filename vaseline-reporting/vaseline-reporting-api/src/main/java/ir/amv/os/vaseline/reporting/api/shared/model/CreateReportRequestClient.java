package ir.amv.os.vaseline.reporting.api.shared.model;

import ir.amv.os.vaseline.reporting.api.shared.enums.ReportOutputType;

import java.util.Map;

/**
 * Created by AMV on 2/10/2016.
 */
public class CreateReportRequestClient {

    private IBaseReportSourceClient reportSource;
    private ReportOutputType outputType;
    private Map<String, Object> argsMap;

    public IBaseReportSourceClient getReportSource() {
        return reportSource;
    }

    public void setReportSource(IBaseReportSourceClient reportSource) {
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
