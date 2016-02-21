package ir.amv.os.vaseline.reporting.api.shared.model;

import ir.amv.os.vaseline.reporting.api.shared.model.file.FileReportResourceImplClient;
import ir.amv.os.vaseline.reporting.api.shared.model.projres.ProjectResourceReportSourceClient;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Created by AMV on 2/17/2016.
 */
@XmlSeeAlso({
        FileReportResourceImplClient.class,
        ProjectResourceReportSourceClient.class
})
public interface IBaseReportSourceClient {
}
