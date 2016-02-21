package ir.amv.os.vaseline.reporting.api.shared.model.projres;

import ir.amv.os.vaseline.base.mapper.server.annot.VaselineMapTo;
import ir.amv.os.vaseline.reporting.api.server.model.projres.ProjectResourceReportSourceServer;
import ir.amv.os.vaseline.reporting.api.shared.model.IBaseReportSourceClient;

/**
 * Created by AMV on 2/17/2016.
 */
@VaselineMapTo(mapToServerClass = ProjectResourceReportSourceServer.class)
public class ProjectResourceReportSourceClient implements IBaseReportSourceClient {

    private String classUsedToLoad;
    private String resourceName;

    public String getClassUsedToLoad() {
        return classUsedToLoad;
    }

    public void setClassUsedToLoad(String classUsedToLoad) {
        this.classUsedToLoad = classUsedToLoad;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
