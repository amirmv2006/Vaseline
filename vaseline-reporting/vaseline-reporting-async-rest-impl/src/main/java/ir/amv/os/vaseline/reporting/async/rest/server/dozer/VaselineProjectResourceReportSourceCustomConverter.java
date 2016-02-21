package ir.amv.os.vaseline.reporting.async.rest.server.dozer;

import ir.amv.os.vaseline.reporting.api.server.model.projres.ProjectResourceReportSourceServer;
import ir.amv.os.vaseline.reporting.api.shared.model.projres.ProjectResourceReportSourceClient;
import org.dozer.DozerConverter;
import org.dozer.MappingException;

/**
 * Created by AMV on 2/17/2016.
 */
public class VaselineProjectResourceReportSourceCustomConverter
        extends DozerConverter<ProjectResourceReportSourceServer, ProjectResourceReportSourceClient> {

    public VaselineProjectResourceReportSourceCustomConverter() {
        super(ProjectResourceReportSourceServer.class, ProjectResourceReportSourceClient.class);
    }

    @Override
    public ProjectResourceReportSourceClient convertTo(ProjectResourceReportSourceServer source, ProjectResourceReportSourceClient destination) {
        if (source.getClassUsedToLoad() != null) {
            destination.setClassUsedToLoad(source.getClassUsedToLoad().getName());
        }
        destination.setResourceName(source.getResourceName());
        return destination;
    }

    @Override
    public ProjectResourceReportSourceServer convertFrom(ProjectResourceReportSourceClient source, ProjectResourceReportSourceServer destination) {
        if (source.getClassUsedToLoad() != null) {
            try {
                destination.setClassUsedToLoad(Class.forName(source.getClassUsedToLoad()));
            } catch (ClassNotFoundException e) {
                throw new MappingException(e);
            }
        }
        destination.setResourceName(source.getResourceName());
        return destination;
    }
}
