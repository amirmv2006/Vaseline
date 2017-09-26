package ir.amv.os.vaseline.reporting.api.server.model.projres;

import ir.amv.os.vaseline.reporting.api.server.model.BaseInputStreamReportSourceImpl;
import ir.amv.os.vaseline.reporting.api.server.model.IBaseReportSourceServer;

import java.io.InputStream;

/**
 * Created by AMV on 2/17/2016.
 */
public class ProjectResourceReportSourceServer
        extends BaseInputStreamReportSourceImpl
        implements IBaseReportSourceServer {

    private Class<?> classUsedToLoad;
    private String resourceName;

    public Class<?> getClassUsedToLoad() {
        return classUsedToLoad;
    }

    public void setClassUsedToLoad(Class<?> classUsedToLoad) {
        this.classUsedToLoad = classUsedToLoad;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    protected InputStream getReportDesignInputStream() {
        return classUsedToLoad.getResourceAsStream(resourceName);
    }
}
