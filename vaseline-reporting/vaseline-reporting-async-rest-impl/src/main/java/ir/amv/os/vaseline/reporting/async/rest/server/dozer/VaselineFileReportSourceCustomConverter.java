package ir.amv.os.vaseline.reporting.async.rest.server.dozer;

import ir.amv.os.vaseline.base.core.config.VaselineCoreConfig;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.reporting.api.server.model.file.FileReportResourceImplServer;
import ir.amv.os.vaseline.reporting.api.shared.model.file.FileReportResourceImplClient;
import org.dozer.DozerConverter;

/**
 * Created by AMV on 2/17/2016.
 */
public class VaselineFileReportSourceCustomConverter
        extends DozerConverter<FileReportResourceImplServer, FileReportResourceImplClient> {

    public VaselineFileReportSourceCustomConverter() {
        super(FileReportResourceImplServer.class, FileReportResourceImplClient.class);
    }

    @Override
    public FileReportResourceImplClient convertTo(FileReportResourceImplServer source, FileReportResourceImplClient destination) {
        destination.setFileCategory(source.getFileCategory());
        destination.setFileId(source.getFileId());
        return destination;
    }

    @Override
    public FileReportResourceImplServer convertFrom(FileReportResourceImplClient source, FileReportResourceImplServer destination) {
        destination.setFileCategory(source.getFileCategory());
        destination.setFileId(source.getFileId());
        destination.setFileApi(VaselineCoreConfig.getBean(IFileApi.class));
        return destination;
    }
}
