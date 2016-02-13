package ir.amv.os.vaseline.reporting.api.server.model;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

import java.io.IOException;

/**
 * Created by AMV on 2/10/2016.
 */
public interface IBaseReportSource {

    JasperReport compile() throws JRException;

    void close() throws IOException;
}
